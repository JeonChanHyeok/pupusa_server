package com.example.pupusa.chatRoom;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatRoomInfoResponse {
    private String chatRoomName;
    private String chatRoomMasterUser;
    private int chatRoomUserCount;
    private String chatRoomContent;
    private String storeCategory;
    private String storeName;
    private String chatRoomAddress;

}
