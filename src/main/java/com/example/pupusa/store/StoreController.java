package com.example.pupusa.store;

import com.example.pupusa.chatMsg.ChatMessageRepository;
import com.example.pupusa.chatMsg.ChatMessageService;
import com.example.pupusa.order.OrderMenu;
import com.example.pupusa.order.OrderResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final StoreRepository storeRepository;

    @RequestMapping(value = "/store/storeload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public StoreInfoResponse storeLoad(String objJson) {
        // 카테고리로 식당 출력하기
        System.out.println("Store access..");
        System.out.println(objJson);


        return
    }
}

