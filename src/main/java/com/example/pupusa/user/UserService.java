package com.example.pupusa.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;


    @Transactional
    public Boolean Login(User user){
        User user_temp = userRepository.findByUserId(user.getUserId());
        if(user_temp != null) {
            if (user.getUserPw().equals(user_temp.getUserPw())) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }
}
