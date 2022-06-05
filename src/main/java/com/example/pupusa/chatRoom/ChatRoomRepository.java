package com.example.pupusa.chatRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findByChatRoomId(Long chatRoomId);
    List<ChatRoom> findAllByIsEnd(boolean a);
    List<ChatRoom> findAllByStore_CategoryAndIsEnd(String category, boolean a);

    @Modifying
    @Transactional
    @Query("UPDATE ChatRoom SET chatRoomState=:chatRoomState WHERE chatRoomId=:chatRoomId")
    int updateChatRoomState(@Param(value="chatRoomState")Integer num, @Param(value="chatRoomId") Long chatRoomId);
}
