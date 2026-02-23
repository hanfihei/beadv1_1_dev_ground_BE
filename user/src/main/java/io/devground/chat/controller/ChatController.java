package io.devground.chat.controller;


import io.devground.chat.client.UserClient;
import io.devground.chat.infrastructure.ChatEventProducer;
import io.devground.chat.model.dto.request.ChatRoomRequest;
import io.devground.chat.model.dto.response.ChatRoomSummary;
import io.devground.chat.model.dto.response.UserResponse;
import io.devground.chat.model.entity.ChatMessages;
import io.devground.chat.model.entity.ChatRoom;
import io.devground.chat.model.event.ChatReadEvent;
import io.devground.chat.service.ChatMessageService;
import io.devground.chat.service.ChatRoomService;
import io.devground.core.model.exception.ServiceException;
import io.devground.core.model.vo.ErrorCode;
import io.devground.core.model.web.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
@Tag(name = "ChatController")

public class ChatController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final ChatEventProducer chatEventProducer;
    private final UserClient userClient;
//    private final ProductClient productClient;

    //채팅방 생성
    @PostMapping("/rooms")
    public BaseResponse<ChatRoom> createOrGetRoom(
            @RequestHeader(value = "X-CODE", required = false) String userCode,
            @Valid @RequestBody ChatRoomRequest request
    ) {
        if (userCode == null || userCode.isBlank()) {
            throw new ServiceException(ErrorCode.XCODE_NOT_FOUND);
        }
        // 본인 상품 채팅 방지
        if (request.getSellerCode() != null && request.getSellerCode().equals(userCode)) {
            throw new ServiceException(ErrorCode.CHAT_SELF_PRODUCT_NOT_ALLOWED);
        }
        return BaseResponse.success(
                CREATED.value(),
                chatRoomService.getOrCreateRoom(
                request.getProductCode(),
                request.getSellerCode(),
                userCode),
                "채팅방이 생성되었습니다."
        );


    }

    //전체 메세지 조회
    @GetMapping("/rooms/{chatId}/messages")
    public BaseResponse<List<ChatMessages>> getMessages(
            @RequestHeader("X-CODE") String userCode,
            @PathVariable String chatId) {
        chatRoomService.getRoom(chatId); // 존재하는 chatId인지
        chatMessageService.markAsRead(chatId, userCode);
        chatEventProducer.sendReadEvent(new ChatReadEvent(chatId, userCode));

        return BaseResponse.success(
                OK.value(),
                chatMessageService.getMessages(chatId),
                "전체 메세지를 조회합니다."

        ) ;
    }


    @PostMapping("/rooms/{chatId}/read")
    public void markRead(
            @RequestHeader("X-CODE") String userCode,
            @PathVariable String chatId
    ) {
        chatRoomService.getRoom(chatId); // 존재하는 chatId인지 확인
        chatMessageService.markAsRead(chatId, userCode);
        chatEventProducer.sendReadEvent(new ChatReadEvent(chatId, userCode));
    }

    //채팅방 목록 (OPEN 상태, 참여자 기준)
    @GetMapping("/rooms")
    public BaseResponse<List<ChatRoomSummary>> listRooms(
            @RequestHeader("X-CODE") String userCode,
            @RequestParam(value = "status", defaultValue = "OPEN") String status
    ) {
        return BaseResponse.success(
                OK.value(),
                chatRoomService.listOpenRoomsForUser(userCode),
                "채팅방 목록을 조회합니다."
        );
    }

    //채팅 나가기
    @PostMapping("/rooms/{chatId}/leave")
    public BaseResponse<ChatRoom> leaveRoom(
            @RequestHeader("X-CODE") String userCode,
            @PathVariable String chatId
    ) {
        if (userCode == null || userCode.isBlank()) {
            throw new ServiceException(ErrorCode.XCODE_NOT_FOUND);
        }
        userClient.getUser(userCode).throwIfNotSuccess();

        return BaseResponse.success(
                OK.value(),
                chatRoomService.leaveRoom(chatId, userCode),
                "채팅방에서 퇴장하였습니다."
        );
    }

    // 채팅에서 상대 프로필 조회
    @GetMapping("/users/{code}")
    public BaseResponse<UserResponse>  getChatUserProfile(
            @RequestHeader("X-CODE") String requesterCode,
            @PathVariable String code
    ) {
        // 요청자 검증
        userClient.getUser(requesterCode).throwIfNotSuccess();
        BaseResponse<UserResponse> target = userClient.getUser(code);
        if (target != null) {
            target.throwIfNotSuccess();
        }
        return BaseResponse.success(
                OK.value(),
                target.data(),
                "상대방의 프로필을 조회합니다."
        );
    }


}
