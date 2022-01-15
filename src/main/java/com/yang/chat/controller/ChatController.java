package com.yang.chat.controller;

import com.yang.chat.dto.ChatRoom;
import com.yang.chat.repository.ChatRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatRepository repository;
    private final AtomicInteger seq = new AtomicInteger(0);


    public ChatController(ChatRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/rooms")
    public String rooms(Model model) {
        model.addAttribute("rooms", repository.getChatRooms());
        return "/chat/room-list";
    }

    @GetMapping("/rooms/{id}")
    public String room (@PathVariable String id, Model model) {
        ChatRoom room = repository.getChatRoom(id);
        model.addAttribute("room", room);
        model.addAttribute("member", "member" +  seq.incrementAndGet());
        return "/chat/room";
    }
}
