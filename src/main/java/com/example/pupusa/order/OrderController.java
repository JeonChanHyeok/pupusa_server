package com.example.pupusa.order;

import com.example.pupusa.chatRoom.ChatRoomRepository;
import com.example.pupusa.store.MenuRepository;
import com.example.pupusa.user.UserRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;

    @RequestMapping(value = "/order/loadroominfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public OrderRoomInfoResponse createUser(String objJson){
        Long roomId = Long.parseLong(objJson);
        OrderRoomInfoResponse orderRoomInfoResponse = new OrderRoomInfoResponse();
        orderRoomInfoResponse.setStoreName(chatRoomRepository.findByChatRoomId(roomId).getStore().getStoreName());
        orderRoomInfoResponse.setPickupLocation(chatRoomRepository.findByChatRoomId(roomId).getChatRoomAddress());
        orderRoomInfoResponse.setMasterId(chatRoomRepository.findByChatRoomId(roomId).getUser().getUserId());
        return orderRoomInfoResponse;
    }

    @RequestMapping(value = "/order/makeorder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public OrderResponseList createOrder(String objJson){
        OrderResponseList orderResponseList = new OrderResponseList();
        JsonParser jp = new JsonParser();
        JsonObject jsonObject = (JsonObject) jp.parse(objJson);
        Long roomId = jsonObject.get("roomId").getAsLong();
        Long menuId = jsonObject.get("menuId").getAsLong();
        String userId = jsonObject.get("userId").getAsString();

        if(chatRoomRepository.findByChatRoomId(roomId).getChatRoomState() != 0){
            orderResponseList.setOrderResponseList(null);
            orderResponseList.setCode(0);
            return orderResponseList;
        }
        OrderMenu order = new OrderMenu();
        order.setChatRoom(chatRoomRepository.findByChatRoomId(roomId));
        order.setEnd(false);
        order.setPay(false);
        order.setMenu(menuRepository.findByMenuId(menuId));
        order.setUser(userRepository.findByUserId(userId));

        orderRepository.save(order);


        List<OrderResponse> orderResponseLists = new ArrayList<>();
        for(OrderMenu o : orderRepository.findAllByChatRoom(chatRoomRepository.findByChatRoomId(roomId))){
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setMenuName(o.getMenu().getMenuName());
            orderResponse.setUserName(o.getUser().getUserName());
            orderResponseLists.add(orderResponse);
        }
        orderResponseList.setOrderResponseList(orderResponseLists);
        orderResponseList.setCode(1);
        return orderResponseList;
    }


    @RequestMapping(value = "/order/loadorderlist", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public OrderResponseList loadOrderList(String objJson){
        Long roomId = Long.parseLong(objJson);
        OrderResponseList orderResponseList = new OrderResponseList();
        if(chatRoomRepository.findByChatRoomId(roomId).getChatRoomState() != 0){
            orderResponseList.setOrderResponseList(null);
            orderResponseList.setCode(0);
            return orderResponseList;
        }
        List<OrderResponse> orderResponseLists = new ArrayList<>();
        for(OrderMenu o : orderRepository.findAllByChatRoom(chatRoomRepository.findByChatRoomId(roomId))){
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setMenuName(o.getMenu().getMenuName());
            orderResponse.setUserName(o.getUser().getUserName());
            orderResponseLists.add(orderResponse);
        }
        orderResponseList.setOrderResponseList(orderResponseLists);
        orderResponseList.setCode(1);
        return orderResponseList;
    }

    @RequestMapping(value = "/order/goPay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void goPay(String objJson){
        Long roomId = Long.parseLong(objJson);
        chatRoomRepository.updateChatRoomState(1, roomId);
    }


}
