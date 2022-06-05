package com.example.pupusa.order;

import com.example.pupusa.chatRoom.ChatRoomRepository;
import com.example.pupusa.user.JoinResponse;
import com.example.pupusa.user.User;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final ChatRoomRepository chatRoomRepository;

    @RequestMapping(value = "/order/loadroominfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public OrderRoomInfoResponse createUser(String objJson){
        Long roomId = Long.parseLong(objJson);
        OrderRoomInfoResponse orderRoomInfoResponse = new OrderRoomInfoResponse();
        orderRoomInfoResponse.setStoreName(chatRoomRepository.findByChatRoomId(roomId).getStore().getStoreName());
        orderRoomInfoResponse.setPickupLocation(chatRoomRepository.findByChatRoomId(roomId).getChatRoomAddress());
        return orderRoomInfoResponse;
    }
    /*@RequestMapping(value = "/order/makeorder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public


    @RequestMapping(value = "/order/loadorderlist", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody*/



}
