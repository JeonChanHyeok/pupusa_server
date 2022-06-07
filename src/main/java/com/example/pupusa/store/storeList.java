package com.example.pupusa.store;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class storeList {

    @SerializedName("storeList")
    private List<StoreResponse> storeResponseList;
}
