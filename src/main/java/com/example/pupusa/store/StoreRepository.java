package com.example.pupusa.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    //List<Store> findAllByStore(Store store);
    //List<Store> findAllByCategory(String category);

    // Select * from store where storeId = id;
    Store findByStoreId(Long id);

    List<Store> findAllByCategory(String category);
}
