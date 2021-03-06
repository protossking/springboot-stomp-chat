package com.yang.chat.repository;

import com.yang.chat.dto.ChatRoom;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collections;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ChatRepository {
    private final Map<String, ChatRoom> chatRoomMap;
    @Getter
    private final Collection<ChatRoom> chatRooms;


    public ChatRepository() {
        chatRoomMap = Collections.unmodifiableMap(
                Stream.of(ChatRoom.create("1번방"), ChatRoom.create("2번방"), ChatRoom.create("3번방"))
                        .collect(Collectors.toMap(ChatRoom::getId, Function.identity())));

        chatRooms = Collections.unmodifiableCollection(chatRoomMap.values());
    }

    public ChatRoom getChatRoom(String id) {
        return chatRoomMap.get(id);
    }





}
