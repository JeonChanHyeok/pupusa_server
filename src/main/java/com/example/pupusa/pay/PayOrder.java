package com.example.pupusa.pay;

import com.example.pupusa.chatRoom.ChatRoom;
import com.example.pupusa.store.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class PayOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payOrderId")
    private Long payOrderId;
    private String want;
    private String payDay;
    private String address;
    private int allPrice;
    private int state;

    @OneToOne
    @JoinColumn(name = "chatRoomId")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "storeId")
    private Store store;


}
