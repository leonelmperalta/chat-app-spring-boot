package com.leonelmperalta.chat.services.implementation;

import com.leonelmperalta.chat.models.Message;
import com.leonelmperalta.chat.repositories.ChatRepository;
import com.leonelmperalta.chat.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public List<Message> getLast10Messages() {
        return chatRepository.findFirst10ByOrderByDateDesc();
    }

    @Override
    public Message saveMessage(Message message) {
        return chatRepository.save(message);
    }
}
