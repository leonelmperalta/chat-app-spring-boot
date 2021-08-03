package com.leonelmperalta.chat.controllers;

import com.leonelmperalta.chat.models.Message;
import com.leonelmperalta.chat.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Random;

@Controller
public class ChatController {

    private ChatService chatService;
    private SimpMessagingTemplate webSocket;

    @Autowired
    public ChatController(ChatService chatService, SimpMessagingTemplate webSocket) {
        this.chatService = chatService;
        this.webSocket = webSocket;
    }

    private final String[] colors = {"red", "purple", "magenta", "green", "blue", "orange"};

    @MessageMapping("/message")
    @SendTo("/chat/message")
    public Message receiveMessage(Message message) {
        message.setDate(new Date().getTime());
        if (message.getType().equals("NEW_USER")) {
            message.setText("New user");
            message.setColor(colors[new Random().nextInt(colors.length - 1)]);
        } else {
            chatService.saveMessage(message);
        }
        return message;
    }

    @MessageMapping("writing")
    @SendTo("/chat/writing")
    public String isWriting(String username){
        return username.concat(" is writing...");
    }

    @MessageMapping("/history")
    public void history(String clientId){
        webSocket.convertAndSend("/chat/history/" + clientId, chatService.getLast10Messages());
    }
}
