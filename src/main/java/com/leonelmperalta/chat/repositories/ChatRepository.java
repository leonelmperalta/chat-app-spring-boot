package com.leonelmperalta.chat.repositories;

import com.leonelmperalta.chat.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<Message, String> {
    public List<Message> findFirst10ByOrderByDateDesc();
}
