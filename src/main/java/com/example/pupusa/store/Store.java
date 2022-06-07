package com.example.pupusa.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity

public class Store {

    @Id
    @Column(nullable = false)
    private Long storeId;


    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String storeAddress;

    @Column(nullable = false)
    private String storeImage;

    @Column
    private String storeInfo;

    @Column(nullable = false)
    private int delieveryFee;

    @Column(nullable = false)
    private String category;
}
