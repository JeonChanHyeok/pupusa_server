package com.example.pupusa.order;

import com.example.pupusa.chatRoom.ChatRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.pupusa.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderMenu, Long> {
    List<OrderMenu> findAllByChatRoom(ChatRoom c);

    List<OrderMenu> findAllByChatRoomAndUser(ChatRoom c, User user);

    @Modifying
    @Transactional
    @Query("UPDATE OrderMenu SET isPay=:isPay WHERE orderId=:orderId")
    int updateOrderMenu(@Param(value = "isPay")Boolean a, @Param(value = "orderId")Long orderId);

}
