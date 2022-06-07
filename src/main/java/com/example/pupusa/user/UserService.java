package com.example.pupusa.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;


    @Transactional
    public Boolean Login(String userId, String userPw){
        User user_temp = userRepository.findByUserId(userId);
        if(user_temp != null && !user_temp.isDeleted()) {
            if (userPw.equals(user_temp.getUserPw())) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }
}
