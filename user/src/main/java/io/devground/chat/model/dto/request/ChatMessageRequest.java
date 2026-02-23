package io.devground.chat.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRequest {

    @NotBlank(message = "MESSAGE_MISSING")
    private String message;

    @NotBlank(message = "SENDER_CODE_MISSING")
    private String senderCode;

    @NotBlank(message = "CHAT_ID_MISSING")
    private String chatId;

    @Override
    public String toString() {
        return "ChatMessageRequest{" +
                "message='" + message + '\'' +
                ", senderCode='" + senderCode + '\'' +
                ", chatId='" + chatId + '\'' +
                '}';
    }
}
