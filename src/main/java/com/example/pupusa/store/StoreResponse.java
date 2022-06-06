package com.example.pupusa.store;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
public class StoreResponse {

    private Long storeId;

    private String storeName;

    private String storeAddress;

    private String storeImage;

    private String storeInfo;

    private int delieveryFee;

    private String category;

}
