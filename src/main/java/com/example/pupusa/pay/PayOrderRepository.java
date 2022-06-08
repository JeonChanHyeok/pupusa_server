package com.example.pupusa.pay;

import com.example.pupusa.chatRoom.ChatRoom;
import com.example.pupusa.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayOrderRepository extends JpaRepository<PayOrder, Long> {
    List<PayOrder> findAllByStoreAndStateAndPayDay(Store s, int state,String day);
}
