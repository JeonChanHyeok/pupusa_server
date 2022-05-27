package com.example.pupusa.Response;

import com.google.gson.annotations.SerializedName;
import lombok.Setter;

@Setter
public class DupResponse {
    @SerializedName("code")
    private int code;

    public int getCode() {
        return code;
    }
}
