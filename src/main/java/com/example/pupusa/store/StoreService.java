package com.example.pupusa.store;

import com.example.pupusa.chatRoom.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service

public class StoreService {
    private final StoreRepository storeRepository;

    @Transactional
    public List<Store> getStoreList(String category, String address) {
        List<Store> temp = storeRepository.findAllByCategory(category);
        return temp;
        //
    }

}
