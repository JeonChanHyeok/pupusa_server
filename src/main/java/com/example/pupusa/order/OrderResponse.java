package com.example.pupusa.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OrderResponse {
    private String menuName;
    private String userName;
    private int price;
}
