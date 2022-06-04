package com.example.pupusa.chatMsg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageResponse {
    private String messageText;
    private String userId;
    private String messageTime;
    private String userName;
}
