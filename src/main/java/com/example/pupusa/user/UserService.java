package com.example.pupusa.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;


    @Transactional
    public Boolean Login(User user){

        User user_temp = userRepository.findByUserid(user.getUserid());
        if(user_temp != null) {
            if (user.getUserpw().equals(user_temp.getUserpw())) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }





}
