package io.devground.chat.service;

import com.mongodb.DuplicateKeyException;
import io.devground.chat.client.ProductClient;
import io.devground.chat.enums.ChatRoomStatus;
import io.devground.chat.model.dto.request.CartProductsRequest;
import io.devground.chat.model.dto.response.CartProductsResponse;
import io.devground.chat.model.dto.response.ChatRoomSummary;
import io.devground.chat.model.entity.ChatRoom;
import io.devground.chat.repository.RoomRepository;
import io.devground.core.model.exception.ServiceException;
import io.devground.core.model.vo.ErrorCode;
import io.devground.core.model.web.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomService {

    private final RoomRepository roomRepository;
    private final ProductClient productClient;

    public ChatRoom getOrCreateRoom(String productCode, String sellerCode, String buyerCode) {
        try {
            return roomRepository.save(
                    ChatRoom.builder()
                            .productCode(productCode)
                            .sellerCode(sellerCode)
                            .buyerCode(buyerCode)
                            .status(ChatRoomStatus.OPEN)
                            .build()
            );
        } catch (DuplicateKeyException e) { // 유니크 제약 터졌을 경우
            return roomRepository
                    .findByProductCodeAndSellerCodeAndBuyerCode(productCode, sellerCode, buyerCode)
                    .orElseThrow(() -> new IllegalStateException("Duplicate ChatRoom, but not found")); // 최후 방어
        }
    }


    public ChatRoom getRoom(String chatId) {
        return roomRepository.findById(chatId)
                .orElseThrow(() -> new ServiceException(ErrorCode.CHAT_ROOM_NOT_FOUND));
    }

    //OPEN상태인 채팅방 조회
    public List<ChatRoomSummary> listOpenRoomsForUser(String userCode) {
        List<ChatRoom> rooms = roomRepository.findByStatusAndSellerCodeOrStatusAndBuyerCode( // 가독성 저하로 jpql 고려
                ChatRoomStatus.OPEN, userCode, ChatRoomStatus.OPEN, userCode //내가 seller일 경우와 buyer인 경우 모두 가져옴
        );

        //productCode만 추출
        List<String> productCodes = rooms.stream()
                .map(ChatRoom::getProductCode)
                .distinct()
                .toList();

        CartProductsRequest request = new CartProductsRequest(productCodes);

        //product feign 클라이언트 호출
        BaseResponse<List<CartProductsResponse>> response =
                productClient.getCartProducts(request);

        List<CartProductsResponse> products = response.data();

        Map<String, String> productTitleMap = new HashMap<>();

        for (CartProductsResponse product : products) {
            productTitleMap.put(
                    product.productCode(),
                    product.title()
            );
        }

        List<ChatRoomSummary> list = new ArrayList<>();

        for (ChatRoom chatRoom : rooms) {
            String title = productTitleMap.get(chatRoom.getProductCode());

            ChatRoomSummary apply = ChatRoomSummary.builder()
                    .id(chatRoom.getId())
                    .productCode(chatRoom.getProductCode())
                    .productTitle(title)
                    .sellerCode(chatRoom.getSellerCode())
                    .buyerCode(chatRoom.getBuyerCode())
                    .status(chatRoom.getStatus())
                    .lastMessage(chatRoom.getLastMessage())
                    .lastMessageAt(chatRoom.getLastMessageAt())
                    .unreadCount(unreadCount(chatRoom, userCode))
                    .build();
            list.add(apply);
        }
        return list;
    }


    public ChatRoom leaveRoom(String chatId, String userCode) {
        ChatRoom room = getRoom(chatId);
        boolean isParticipant = userCode.equals(room.getSellerCode()) || userCode.equals(room.getBuyerCode());
        if (!isParticipant) {
            throw new ServiceException(ErrorCode.CHAT_ROOM_ACCESS_DENIED);
        }
        room.close();
        return roomRepository.save(room);
    }

    public ChatRoom saveRoom(ChatRoom room) {
        return roomRepository.save(room);
    }

    private long unreadCount(ChatRoom room, String userCode) {
        if (userCode == null) {
            return 0;
        }
        if (userCode.equals(room.getSellerCode())) {
            return room.getSellerUnreadCount();
        }
        if (userCode.equals(room.getBuyerCode())) {
            return room.getBuyerUnreadCount();
        }
        return 0;
    }
}
