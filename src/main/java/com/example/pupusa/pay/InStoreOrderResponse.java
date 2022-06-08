package com.example.pupusa.pay;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InStoreOrderResponse {
    private Long roomId;
    private String address;
    private String menusName;
    private String date;
    private int price;
}
