package com.example.pupusa.chatRoom;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChatRoomList {

    @SerializedName("chatRoomResponses")
    private List<ChatRoomResponse> chatRoomResponses;

}
