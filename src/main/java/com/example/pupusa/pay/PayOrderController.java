package com.example.pupusa.pay;

import com.example.pupusa.chatRoom.ChatRoomRepository;
import com.example.pupusa.menu.Menu;
import com.example.pupusa.order.OrderMenu;
import com.example.pupusa.order.OrderRepository;
import com.example.pupusa.store.StoreRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PayOrderController {
    private final PayOrderRepository payOrderRepository;
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/sendbill")
    public void addPayOrder(String objJson){
        JsonParser jp = new JsonParser();
        JsonObject jsonObject = (JsonObject) jp.parse(objJson);
        Long chatRoomId = jsonObject.get("roomId").getAsLong();
        //요청사항
        String msg = jsonObject.get("msg").getAsString();
        int price = jsonObject.get("allPrice").getAsInt();
        String address = jsonObject.get("address").getAsString();


        chatRoomRepository.updateChatRoomState(2, chatRoomId);

        PayOrder p = new PayOrder();
        LocalDate now_day = LocalDate.now();
        String today = now_day.toString();
        p.setPayDay(today);
        p.setAllPrice(price);
        p.setState(0);
        p.setStore(chatRoomRepository.findByChatRoomId(chatRoomId).getStore());
        p.setWant(msg);
        p.setAddress(address);
        p.setChatRoom(chatRoomRepository.findByChatRoomId(chatRoomId));
        payOrderRepository.save(p);
        simpMessagingTemplate.convertAndSend("/topic/chat/pay/" + chatRoomId,"end");
        simpMessagingTemplate.convertAndSend("/topic/chat/order/" + p.getStore().getStoreId(), "temp");
    }

    @RequestMapping(value = "/store/orderload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public InStoreOrderResponseList orderLoadInStore(String objJson){

        Long storeId = Long.parseLong(objJson);

        LocalDate now_day = LocalDate.now();
        String today = now_day.toString();

        //주문 승인 대기중
        List<PayOrder> list_0 = payOrderRepository.findAllByStoreAndStateAndPayDay(storeRepository.findByStoreId(storeId), 0, today);
        //주문 요리중
        List<PayOrder> list_1 = payOrderRepository.findAllByStoreAndStateAndPayDay(storeRepository.findByStoreId(storeId), 1, today);
        //주문 배달완료
        List<PayOrder> list_2 = payOrderRepository.findAllByStoreAndStateAndPayDay(storeRepository.findByStoreId(storeId), 2, today);

        List<PayOrder> return_list = new ArrayList<>();
        return_list.addAll(list_0);
        return_list.addAll(list_1);
        return_list.addAll(list_2);

        InStoreOrderResponseList inStoreOrderResponseList = new InStoreOrderResponseList();
        List<InStoreOrderResponse> temp_list = new ArrayList<>();
        for(PayOrder p: return_list){
            InStoreOrderResponse inStoreOrderResponse = new InStoreOrderResponse();
            inStoreOrderResponse.setAddress(p.getChatRoom().getChatRoomAddress());
            inStoreOrderResponse.setDate(p.getPayDay());
            inStoreOrderResponse.setPrice(p.getAllPrice());
            inStoreOrderResponse.setRoomId(p.getChatRoom().getChatRoomId());
            List<String> menus = new ArrayList<>();
            List<String> menuNames = new ArrayList<>();
            List<Integer> menuCount = new ArrayList<>();
            for(OrderMenu o:orderRepository.findAllByChatRoom(p.getChatRoom())){
                menus.add(o.getMenu().getMenuName());
                if(!menuNames.contains(o.getMenu().getMenuName())){
                    menuNames.add(o.getMenu().getMenuName());
                }
            }
            for(String s: menuNames){
                int count = Collections.frequency(menus, s);
                menuCount.add(count);
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < menuNames.size();i++){
                sb.append(menuNames.get(i) + " x " + menuCount.get(i));
            }
            inStoreOrderResponse.setMenusName(sb.toString());
            temp_list.add(inStoreOrderResponse);
        }
        inStoreOrderResponseList.setInStoreOrderResponseList(temp_list);
        return inStoreOrderResponseList;
    }
}
