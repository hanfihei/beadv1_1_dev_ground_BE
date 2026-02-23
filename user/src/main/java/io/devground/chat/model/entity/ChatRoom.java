package io.devground.chat.model.entity;

import io.devground.chat.enums.ChatRoomStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "chatRooms")
@CompoundIndex(
        name = "uk_chatroom_product_seller_buyer",
        def = "{'productCode': 1, 'sellerCode': 1, 'buyerCode': 1}",
        unique = true
)
@NoArgsConstructor
@Getter
//@AllArgsConstructor
public class ChatRoom  {

    @Id
    private String id;

    private String chatCode = UUID.randomUUID().toString();

    private String productCode;

    private String sellerCode;

    private String buyerCode;

    private ChatRoomStatus status;

    private String lastMessage;
    private LocalDateTime lastMessageAt;

    private LocalDateTime sellerLastReadAt;
    private LocalDateTime buyerLastReadAt;

    private int sellerUnreadCount;
    private int buyerUnreadCount;


    @Builder
    public ChatRoom(String productCode, String sellerCode, String buyerCode, ChatRoomStatus status) {
        this.id = UUID.randomUUID().toString();
        this.productCode = productCode;
        this.chatCode = UUID.randomUUID().toString();
        this.sellerCode = sellerCode;
        this.buyerCode = buyerCode;
        this.status = status;
    }

    public void close() {
        this.status = ChatRoomStatus.CLOSED;
    }


    public void updateMessage(String senderCode, String message, LocalDateTime createdAt) {
        this.lastMessage = message;
        this.lastMessageAt = createdAt;

        if (senderCode.equals(sellerCode)) {
            // seller가 메세지를 보냈을 경우
            buyerUnreadCount++;
            sellerLastReadAt = createdAt;
            return;
        }
        if (senderCode.equals(buyerCode)) {
            // buyer가 메세지를 보냈을 경우
            sellerUnreadCount++;
            buyerLastReadAt = createdAt;
        }
    }

    public void markRead(String readerCode, LocalDateTime readAt) {

        //seller가 읽었을 경우
        if (readerCode.equals(sellerCode)) {
            sellerLastReadAt = readAt;
            sellerUnreadCount = 0;
            return;
        }
        //buyer가 읽었을 경우
        if (readerCode.equals(buyerCode)) {
            buyerLastReadAt = readAt;
            buyerUnreadCount = 0;
        }
    }
}
