package com.example.pupusa.chatRoom;

import com.example.pupusa.store.Store;
import com.example.pupusa.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ChatRoom {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatRoomId")
    private Long chatRoomId;

    @Column(name = "chatRoomName")
    private String chatRoomName;

    @Column(name= "chatRoomAddress")
    private String chatRoomAddress;

    @Column(name= "chatRoomInfo")
    private String chatRoomInfo;

    @OneToOne
    @JoinColumn(name = "roomMasterUserId")
    private User user;

    @OneToOne
    @JoinColumn(name = "storeId")
    private Store store;

    @Column(name = "isEnd",nullable = false)
    private boolean isEnd;

    @Column(name = "chatRoomState")
    private Integer chatRoomState;

}
