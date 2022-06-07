package com.example.pupusa.chatMsg;


import com.example.pupusa.chatRoom.ChatRoomRepository;
import com.example.pupusa.user.UserRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ChatMessageRepository chatMessageRepository;

    //채팅메시지 불러오기
    @RequestMapping(value = "/chat/loadchatmsg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ChatMessageList loadChatMsg(String objJson){
        ChatMessageList temp = new ChatMessageList();
        try{
            temp.setChatMessageList(chatMessageService.getChatMessage(Long.parseLong(objJson)));
            return temp;
        }catch (Exception e){
            return null;
        }
    }

    //채팅 입력 받기
    @MessageMapping("/sendmsg")
    public void sendMsg(String str) throws Exception{
        JsonParser jp = new JsonParser();
        JsonObject jsonObject = (JsonObject) jp.parse(str);
        String msg = jsonObject.get("messageText").getAsString();
        String msgTime = jsonObject.get("messageTime").getAsString();
        String roomId = jsonObject.get("chatRoomId").getAsString();
        Long roomId_long = Long.parseLong(roomId);
        String userId = jsonObject.get("userId").getAsString();
        chatMessageRepository.save(chatMessageService.makeMsg(msg,roomId_long,userId,msgTime));
        simpMessagingTemplate.convertAndSend("/topic/chat/room/" + roomId,userId);
    }
}
