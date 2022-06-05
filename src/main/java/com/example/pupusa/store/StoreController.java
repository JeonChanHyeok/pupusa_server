package com.example.pupusa.store;

import com.example.pupusa.chatMsg.ChatMessageRepository;
import com.example.pupusa.chatMsg.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final StoreRepository storeRepository;

    @RequestMapping(value = "/store/storeload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public StoreResponse storeLoad() {
        System.out.println("Store access..");
        StoreResponse temp = new StoreResponse();

        try {
            temp.setStoreList(storeService.getStoreList());
            return temp;
        } catch (Exception e){
            return temp;
        }

    }

}
