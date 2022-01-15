package com.yang.chat.controller;

import com.yang.chat.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatMessageController {

    private final SimpMessagingTemplate template;

    public ChatMessageController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/chat/join")
    public void join(ChatMessage message) {
        message.setMessage(message.getWriter() + "님이 입장하셨습니다");
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);

    }
    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
    }







}
