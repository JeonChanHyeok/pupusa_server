package com.example.pupusa.chatRoom;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomResponse {
    @SerializedName("chatRoomId")
    private Long chatRoomId;

    @SerializedName("chatRoomName")
    private String chatRoomName;

    @SerializedName("chatRoomStoreName")
    private String chatRoomStoreName;

    @SerializedName("inRoomUserCount")
    private int inRoomUserCount;

    @SerializedName("chatRoomAddress")
    private String chatRoomAddress;

}
