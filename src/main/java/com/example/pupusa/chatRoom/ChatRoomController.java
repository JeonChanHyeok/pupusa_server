package com.example.pupusa.chatRoom;

import com.example.pupusa.map.AddressToXY;
import com.example.pupusa.store.StoreRepository;
import com.example.pupusa.user.User;
import com.example.pupusa.user.UserRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomService chatRoomService;
    private final UserRepository userRepository;
    private final ChatRoomJoinRepository chatRoomJoinRepository;
    private final StoreRepository storeRepository;


    //채팅방 목록 불러오기
    @RequestMapping(value = "/chat/roomload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ChatRoomList sendChatRoomList(String objJson){
        JsonParser jp = new JsonParser();
        JsonObject jsonObject = (JsonObject) jp.parse(objJson);
        String userId = jsonObject.get("requestUserId").getAsString();
        String category = jsonObject.get("requestCategory").getAsString();

        Double user_x =  AddressToXY.getX(userRepository.findByUserId(userId).getUserAddress());
        Double user_y =  AddressToXY.getY(userRepository.findByUserId(userId).getUserAddress());

        List<ChatRoom> findChatRoomList;

        if(category.equals("전체")){
            findChatRoomList = chatRoomRepository.findAllByIsEnd(false);
        }else{
            findChatRoomList = chatRoomRepository.findAllByStore_CategoryAndIsEnd(category,false);
        }

        List<ChatRoomResponse> chatRoomResponseList = new ArrayList<>();
        ChatRoomList chatRoomList = new ChatRoomList();
        ChatRoomResponse chatRoomResponse = new ChatRoomResponse();

        Double chatRoom_x, chatRoom_y;
        for(ChatRoom c:findChatRoomList){
            chatRoom_x = AddressToXY.getX(c.getChatRoomAddress());
            chatRoom_y = AddressToXY.getY(c.getChatRoomAddress());
            Double distance = AddressToXY.distance(user_x,user_y,chatRoom_x,chatRoom_y,"meter");
            if(distance<100D){
                chatRoomResponse.setChatRoomId(c.getChatRoomId());
                chatRoomResponse.setChatRoomAddress(c.getChatRoomAddress());
                chatRoomResponse.setChatRoomName(c.getChatRoomName());
                chatRoomResponse.setChatRoomStoreName(c.getStore().getStoreName());
                chatRoomResponse.setInRoomUserCount(chatRoomJoinRepository.countChatRoomJoinByChatRoom(c));
                chatRoomResponseList.add(chatRoomResponse);
            }
        }
        chatRoomList.setChatRoomResponses(chatRoomResponseList);
        return chatRoomList;
    }

    //채팅방 만들기
    @RequestMapping(value = "/chat/roommake", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Long addChatRoomList(String objJson){
        JsonParser jp = new JsonParser();
        JsonObject jsonObject = (JsonObject) jp.parse(objJson);
        Long chatRoomStoreId = jsonObject.get("chatRoomStoreId").getAsLong();
        String userId = jsonObject.get("userId").getAsString();
        String chatRoomName = jsonObject.get("chatRoomName").getAsString();
        String chatRoomAddress = jsonObject.get("chatRoomAddress").getAsString();
        String chatRoomInfo = jsonObject.get("chatRoomInfo").getAsString();

        ChatRoom chatroom = new ChatRoom();
        chatroom.setChatRoomName(chatRoomName);
        chatroom.setStore(storeRepository.findByStoreId(chatRoomStoreId));
        chatroom.setUser(userRepository.findByUserId(userId));
        chatroom.setChatRoomAddress(chatRoomAddress);
        chatroom.setChatRoomInfo(chatRoomInfo);
        chatRoomRepository.save(chatroom);
        ChatRoomJoin chatRoomJoin = new ChatRoomJoin();
        chatRoomJoin.setChatRoom(chatroom);
        chatRoomJoin.setUser(userRepository.findByUserId(userId));
        chatRoomJoinRepository.save(chatRoomJoin);
        return chatroom.getChatRoomId();
    }
    //채팅방 입장
    @RequestMapping(value = "/chat/loadroominfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ChatRoomInfoResponse loadChatRoomInfo(String objJson){
        ChatRoomInfoResponse chatRoomInfoResponse = new ChatRoomInfoResponse();
        Long roomId = Long.parseLong(objJson);
        ChatRoom c = chatRoomRepository.findByChatRoomId(roomId);
        chatRoomInfoResponse.setChatRoomContent(c.getChatRoomInfo());
        chatRoomInfoResponse.setChatRoomMasterUser(c.getUser().getUserName());
        chatRoomInfoResponse.setStoreCategory(c.getStore().getCategory());
        chatRoomInfoResponse.setStoreName(c.getStore().getStoreName());
        chatRoomInfoResponse.setChatRoomName(c.getChatRoomName());
        chatRoomInfoResponse.setChatRoomUserCount(chatRoomJoinRepository.countChatRoomJoinByChatRoom(c));
        chatRoomInfoResponse.setChatRoomAddress(c.getChatRoomAddress());
        return chatRoomInfoResponse;
    }
    @RequestMapping(value = "/chat/joinroom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int joinChatRoom(String objJson){
        JsonParser jp = new JsonParser();
        JsonObject jsonObject = (JsonObject) jp.parse(objJson);
        Long roomId = jsonObject.get("roomId").getAsLong();
        ChatRoom chatRoom = chatRoomRepository.findByChatRoomId(roomId);
        String userId = jsonObject.get("userId").getAsString();
        User user = userRepository.findByUserId(userId);
        ChatRoomJoin c = chatRoomJoinRepository.findByChatRoomAndUserAndIsEnd(chatRoom, user, false);
        if(c == null){
            if(chatRoomJoinRepository.countChatRoomJoinByChatRoom(chatRoom) >= 8){
                return 2;
            }else{
                ChatRoomJoin chatRoomJoin = new ChatRoomJoin();
                chatRoomJoin.setChatRoom(chatRoom);
                chatRoomJoin.setUser(user);
                chatRoomJoin.setEnd(false);
                chatRoomJoinRepository.save(chatRoomJoin);
                return 1;
            }
        }else{
            return 3;
        }
    }

    //채팅방 나가기
    @RequestMapping(value = "/chat/exitroom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void exitChatRoom(String objJson){
    }





}
