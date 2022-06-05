package com.example.pupusa.chatRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findByChatRoomId(Long chatRoomId);
    List<ChatRoom> findAllByIsEnd(boolean a);
    List<ChatRoom> findAllByStore_CategoryAndIsEnd(String category, boolean a);
}
