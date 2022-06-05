package com.example.pupusa.order;

import com.example.pupusa.chatRoom.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderMenu, Long> {
    List<OrderMenu> findAllByChatRoom(ChatRoom c);
}
