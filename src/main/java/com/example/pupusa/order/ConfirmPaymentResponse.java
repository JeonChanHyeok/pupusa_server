package com.example.pupusa.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConfirmPaymentResponse {
    private List<Long> menuId;
    private List<String> userId;
    private List<String> userName;
    private List<String> menuName;
    private List<Integer> menuPrice;
    private List<Boolean> isPay;
    private String masterUserId;
    private String unLuckyManId;
    private String roomStoreName;
    private String roomPickUpAddress;
    private int roomDeliPee;
    private int userCount;
}
