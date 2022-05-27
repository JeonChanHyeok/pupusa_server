package com.example.pupusa.chatRoom;

import com.example.pupusa.user.UserRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomRepository chatRepository;
    private final ChatRoomService chatRoomService;
    private final UserRepository userRepository;
    private final ChatRoomJoinRepository chatRoomJoinRepository;


    //채팅방 목록 불러오기
    @RequestMapping(value = "/chat/roomload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ChatRoomList sendChatRoomList(){
        ChatRoomList temp = new ChatRoomList();
        try{
            temp.setChatroomlist(chatRoomService.getChatRoomList());
            return temp;
        }catch (Exception e){
            return temp;
        }
    }

    //채팅방 만들기
    @RequestMapping(value = "/chat/roommake", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ChatRoomList addChatRoomList(String objJson){
        System.out.println(objJson);
        JsonParser jp = new JsonParser();
        JsonObject jsonObject = (JsonObject) jp.parse(objJson);
        String name = jsonObject.get("chatRoomName").getAsString();
        ChatRoomList temp = new ChatRoomList();
        chatRoomService.makeChatRoom(name);
        try{
            temp.setChatroomlist(chatRoomService.getChatRoomList());
            return temp;
        }catch (Exception e){
            return temp;
        }
    }

    //채팅방 입장
    @RequestMapping(value = "/chat/joinroom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void joinChatRoom(String objJson){
        JsonParser jp = new JsonParser();
        JsonObject jsonObject = (JsonObject) jp.parse(objJson);
        String roomId = jsonObject.get("roomId").getAsString();
        Long roomId_long = Long.parseLong(roomId);
        String userId = jsonObject.get("userId").getAsString();
        chatRoomService.joinChatRoom(roomId_long, userId);
    }

    //채팅방 나가기
    @RequestMapping(value = "/chat/exitroom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void exitChatRoom(String objJson){
        JsonParser jp = new JsonParser();
        JsonObject jsonObject = (JsonObject) jp.parse(objJson);
        String roomId = jsonObject.get("roomId").getAsString();
        Long roomId_long = Long.parseLong(roomId);
        String userId = jsonObject.get("userId").getAsString();
        chatRoomJoinRepository.delete(chatRoomJoinRepository.findByChatRoomAndUser(chatRepository.findByChatRoomId(roomId_long),userRepository.findByUserId(userId)));
    }
}
