package com.example.pupusa.menu;

import com.example.pupusa.store.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Menu {
    @Id
    @Column(nullable = false)
    private Long MenuId;

    @ManyToOne
    @JoinColumn(name = "storeId")
    private Store store;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    private String menuImage;

    @Column(nullable = false)
    private int menuPrice;

    @Column(nullable = false)
    private String menuInfo;


}
