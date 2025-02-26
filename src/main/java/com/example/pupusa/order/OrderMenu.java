package com.example.pupusa.order;

import com.example.pupusa.chatRoom.ChatRoom;
import com.example.pupusa.menu.Menu;

import com.example.pupusa.user.User;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class OrderMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long orderId;
    private boolean isEnd;
    private boolean isPay;

    @ManyToOne
    @JoinColumn(name = "menuId")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private ChatRoom chatRoom;



}
