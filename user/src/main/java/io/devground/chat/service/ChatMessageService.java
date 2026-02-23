package io.devground.chat.service;

import io.devground.chat.client.UserClient;
import io.devground.chat.model.dto.request.ChatMessageRequest;
import io.devground.chat.model.entity.ChatMessages;
import io.devground.chat.model.entity.ChatRoom;
import io.devground.chat.repository.MessageRepository;
import io.devground.core.model.exception.ServiceException;
import io.devground.core.model.vo.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final MessageRepository messageRepository;
    private final ChatRoomService chatRoomService;
    private final UserClient userClient;

    // TODO: 트래픽 생길 시 sendMessage를 command서비스로 빼고 sendMessage에 트랜잭션을 걸어야함 (현재는 self-invocation 문제로 보류)
    public ChatMessages sendValidatedMessage(String userCodeHeader, ChatMessageRequest request) {
        String chatId = request.getChatId();
        String senderCode = request.getSenderCode();

        if (userCodeHeader == null || userCodeHeader.isBlank()) {
            throw new ServiceException(ErrorCode.XCODE_NOT_FOUND);
        }

        // 사용자 존재 확인
        userClient.getUser(userCodeHeader).throwIfNotSuccess();

        // 방 존재 및 참여자 여부 확인
        ChatRoom room = chatRoomService.getRoom(chatId);
        boolean isParticipant = userCodeHeader.equals(room.getSellerCode()) || userCodeHeader.equals(room.getBuyerCode());
        if (!isParticipant) {
            throw new ServiceException(ErrorCode.CHAT_ROOM_ACCESS_DENIED);
        }
        return sendMessage(chatId, senderCode, request.getMessage());
    }

    public ChatMessages sendMessage(String chatId, String senderCode, String message) {
        ChatMessages saved = messageRepository.save(
                ChatMessages.builder()
                        .chatId(chatId)
                        .senderCode(senderCode)
                        .message(message)
                        .build()
        );
        ChatRoom room = chatRoomService.getRoom(chatId);
        room.updateMessage(senderCode, message, saved.getCreatedAt());
        chatRoomService.saveRoom(room);
        return saved;
    }

    public List<ChatMessages> getMessages(String chatId) {
        return messageRepository.findByChatIdOrderByCreatedAtAsc(chatId);
    }

    public void markAsRead(String chatId, String readerCode) {
        ChatRoom room = chatRoomService.getRoom(chatId);
        room.markRead(readerCode, LocalDateTime.now());
        chatRoomService.saveRoom(room);
    }




}
