package com.example.pupusa.chatRoom;

import com.example.pupusa.store.Store;
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

    @OneToOne
    @JoinColumn(name = "storeId")
    private Store store;

    @Column(nullable = false)
    private boolean isEnd;
}
