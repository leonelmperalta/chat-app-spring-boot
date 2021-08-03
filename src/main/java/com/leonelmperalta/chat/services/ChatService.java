package com.leonelmperalta.chat.services;

import com.leonelmperalta.chat.models.Message;

import java.util.List;

public interface ChatService {
    public List<Message> getLast10Messages();
    public Message saveMessage(Message message);
}
