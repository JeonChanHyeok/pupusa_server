package com.example.pupusa.chatRoom;

import com.example.pupusa.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ChatRoomJoin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    boolean isEnd;

    @ManyToOne
    @JoinColumn(name =  "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chatRoomId")
    private ChatRoom chatRoom;


}
