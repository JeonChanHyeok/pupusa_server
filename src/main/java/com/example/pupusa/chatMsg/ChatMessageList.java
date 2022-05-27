package com.example.pupusa.chatMsg;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatMessageList {

    @SerializedName("chatMessageList")
    private List<ChatMessageResponse> chatMessageList;
}
