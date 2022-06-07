package com.example.pupusa.notice;

import com.example.pupusa.chatRoom.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public List<Notice> getNoticeList(){
        List<Notice> temp = noticeRepository.findAll();
        return temp;
    }

}
