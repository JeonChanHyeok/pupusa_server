package com.example.pupusa.chatRoom;

import com.example.pupusa.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomJoinRepository extends JpaRepository<ChatRoomJoin, Long>{
    public ChatRoomJoin findByChatRoomAndUserAndIsEnd(ChatRoom chatRoom, User user, boolean a);
    public int countChatRoomJoinByChatRoomAndIsEnd(ChatRoom c, Boolean a);
    public List<ChatRoomJoin> findAllByChatRoomAndIsEnd(ChatRoom chatRoom, boolean a);
}