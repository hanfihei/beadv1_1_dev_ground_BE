package io.devground.chat.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chatMessages")
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ChatMessages {

    @Id
    private String id;
    private String chatId;
    private String message;
    private String senderCode;
    private LocalDateTime createdAt;

    @Builder
    public ChatMessages(String chatId, String senderCode, String message, LocalDateTime createdAt) {
        this.chatId = chatId;
        this.senderCode = senderCode;
        this.message = message;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

}
