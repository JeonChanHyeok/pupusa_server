package com.example.pupusa.chatRoom;

import com.example.pupusa.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomJoinRepository extends JpaRepository<ChatRoomJoin, Long>{
    public ChatRoomJoin findByChatRoomAndUser(ChatRoom chatRoom, User user);
}