package com.example.pupusa.order;

import com.example.pupusa.chatRoom.ChatRoomJoin;
import com.example.pupusa.chatRoom.ChatRoomJoinRepository;
import com.example.pupusa.chatRoom.ChatRoomRepository;

import com.example.pupusa.menu.MenuRepository;

import com.example.pupusa.menu.MenuRepository;
import com.example.pupusa.user.User;

import com.example.pupusa.user.UserRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final ChatRoomJoinRepository chatRoomJoinRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;

    private final SimpMessagingTemplate simpMessagingTemplate;


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

        List<ChatRoomJoin> joinList = chatRoomJoinRepository.findAllByChatRoomAndIsEnd(chatRoomRepository.findByChatRoomId(roomId), false);
        int count = joinList.size();
        int unlucky = (int) ((Math.random()*10000)%count);
        chatRoomRepository.updateUnluckyMan(joinList.get(unlucky).getUser(),roomId);

        chatRoomRepository.updateChatRoomState(1, roomId);
    }

    @RequestMapping(value = "/pay/loadPayList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ConfirmPaymentResponse loadPayList(String objJson){
        ConfirmPaymentResponse confirmPaymentResponse = new ConfirmPaymentResponse();
        Long roomId = Long.parseLong(objJson);
        List<Long> menuId = new ArrayList<>();
        List<String> userId = new ArrayList<>();
        List<String> userName = new ArrayList<>();
        List<String> menuName = new ArrayList<>();
        List<Integer> menuPrice = new ArrayList<>();
        List<Boolean> isPay = new ArrayList<>();
        for(OrderMenu o : orderRepository.findAllByChatRoom(chatRoomRepository.findByChatRoomId(roomId))){
            menuId.add(o.getMenu().getMenuId());
            userId.add(o.getUser().getUserId());
            userName.add(o.getUser().getUserName());
            menuName.add(o.getMenu().getMenuName());
            menuPrice.add(o.getMenu().getMenuPrice());
            isPay.add(o.isPay());
        }
        confirmPaymentResponse.setMenuId(menuId);
        confirmPaymentResponse.setUserId(userId);
        confirmPaymentResponse.setUserName(userName);
        confirmPaymentResponse.setMenuName(menuName);
        confirmPaymentResponse.setMenuPrice(menuPrice);
        confirmPaymentResponse.setIsPay(isPay);
        confirmPaymentResponse.setMasterUserId(chatRoomRepository.findByChatRoomId(roomId).getUser().getUserId());
        confirmPaymentResponse.setRoomDeliPee(chatRoomRepository.findByChatRoomId(roomId).getStore().getDelieveryFee());
        confirmPaymentResponse.setRoomPickUpAddress(chatRoomRepository.findByChatRoomId(roomId).getChatRoomAddress());
        confirmPaymentResponse.setRoomStoreName(chatRoomRepository.findByChatRoomId(roomId).getStore().getStoreName());
        confirmPaymentResponse.setUnLuckyManId(chatRoomRepository.findByChatRoomId(roomId).getUnLuckyMan().getUserId());
        confirmPaymentResponse.setUserCount(chatRoomJoinRepository.countChatRoomJoinByChatRoomAndIsEnd(chatRoomRepository.findByChatRoomId(roomId), false));
        return confirmPaymentResponse;
    }

    //결제
    @MessageMapping("/pay")
    public void sendMsg(String str) throws Exception{
        JsonParser jp = new JsonParser();
        JsonObject jsonObject = (JsonObject) jp.parse(str);
        String roomId = jsonObject.get("roomId").getAsString();
        Long roomId_long = Long.parseLong(roomId);
        String userId = jsonObject.get("userId").getAsString();
        List<OrderMenu> orderMenuList = orderRepository.findAllByChatRoomAndUser(chatRoomRepository.findByChatRoomId(roomId_long), userRepository.findByUserId(userId));
        for(OrderMenu o : orderMenuList){
            orderRepository.updateOrderMenu(true, o.getOrderId());
        }
        simpMessagingTemplate.convertAndSend("/topic/chat/pay/" + roomId,userId);
    }

}
