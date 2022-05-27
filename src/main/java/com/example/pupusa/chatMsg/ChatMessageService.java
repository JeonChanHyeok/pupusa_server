package com.example.pupusa.chatMsg;

import com.example.pupusa.chatRoom.ChatRoomRepository;
import com.example.pupusa.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage makeMsg(String msg, Long roomId, String userId){
        ChatMessage c = new ChatMessage();
        c.setChatRoom(chatRoomRepository.findByChatRoomId(roomId));
        c.setMessageText(msg);
        c.setUser(userRepository.findByUserId(userId));
        return c;
    }

    public List<ChatMessageResponse> getChatMessage(Long roomId){
        List<ChatMessageResponse> temp = new ArrayList<>();
        for(ChatMessage c : chatMessageRepository.findAllByChatRoom(chatRoomRepository.findByChatRoomId(roomId))){
            ChatMessageResponse cmr = new ChatMessageResponse();
            cmr.setMessage(c.getMessageText());
            cmr.setUserId(c.getUser().getUserId());
            temp.add(cmr);
        }
        return temp;
    }

}
