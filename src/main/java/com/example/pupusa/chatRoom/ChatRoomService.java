package com.example.pupusa.chatRoom;

import com.example.pupusa.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomJoinRepository chatRoomJoinRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<ChatRoom> getChatRoomList(){
        List<ChatRoom> temp = chatRoomRepository.findAll();
        return temp;
    }

    public void makeChatRoom(String roomName){
        ChatRoom c = new ChatRoom();
        c.setChatRoomName(roomName);
        chatRoomRepository.save(c);
    }

}
