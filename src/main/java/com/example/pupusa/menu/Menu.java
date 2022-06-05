package com.example.pupusa.menu;

import com.example.pupusa.store.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Menu {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuId")
    private Long menuId;

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

    @Column(nullable = false)
    private String menuCategory;


}
