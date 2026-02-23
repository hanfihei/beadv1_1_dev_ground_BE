package io.devground.chat.repository;

import io.devground.chat.model.entity.ChatMessages;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<ChatMessages, String> {

    List<ChatMessages> findByChatIdOrderByCreatedAtAsc(String chatId);
}
