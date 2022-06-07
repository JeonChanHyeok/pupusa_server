package com.example.pupusa.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @RequestMapping(value = "/notice/noticeload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public NoticeResponse noticeLoad(){
        NoticeResponse temp = new NoticeResponse();
        try {
            temp.setNoticeList(noticeService.getNoticeList());
            return temp;
        }catch (Exception e){
            return temp;
        }
    }
}
