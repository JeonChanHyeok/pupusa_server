package com.example.pupusa.chatRoom;

import com.example.pupusa.store.StoreRepository;
import com.example.pupusa.user.UserRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public void sendChatRoomList(){

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
    @RequestMapping(value = "/chat/joinroom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void joinChatRoom(String objJson){
    }

    //채팅방 나가기
    @RequestMapping(value = "/chat/exitroom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void exitChatRoom(String objJson){
    }





}
