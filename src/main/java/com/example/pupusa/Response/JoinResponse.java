package com.example.pupusa.Response;

import com.google.gson.annotations.SerializedName;
import lombok.Setter;

@Setter
public class JoinResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
