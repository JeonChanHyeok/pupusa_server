package com.example.pupusa.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;


    @RequestMapping(value = "/user/join", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JoinResponse createUser(String objJson){
        Gson gson = new Gson();
        JoinResponse temp = new JoinResponse();
        try{
            User user = gson.fromJson(objJson, User.class);
            userRepository.save(user);
            temp.setCode(1);
            temp.setMessage("회원가입 성공");
            return temp;
        }catch (Exception e){
            e.printStackTrace();
            temp.setCode(2);
            temp.setMessage("회원가입 실패");
            return temp;
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public LoginResponse loginUser(String objJson){
        LoginResponse temp = new LoginResponse();
        JsonParser jp = new JsonParser();
        JsonObject jsonObject = (JsonObject) jp.parse(objJson);
        String userId = jsonObject.get("userId").getAsString();
        String userPw = jsonObject.get("userPw").getAsString();
        try{
            if(userService.Login(userId, userPw)){
                temp.setCode(1);
                temp.setUserId(userId);
                temp.setUserName(userRepository.findByUserId(userId).getUserName());
                temp.setMessage("어서오세요! " + userRepository.findByUserId(userId).getUserName() + "님!");
            }else{
                temp.setCode(2);
                temp.setMessage("로그인 실패하셨습니다.");
            }
            return temp;
        }catch (Exception e){
            e.printStackTrace();
            temp.setCode(2);
            temp.setMessage("로그인 실패하셨습니다.");
            return temp;
        }
    }

    @RequestMapping(value = "/user/joindupchk", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DupResponse dupCheck(String objJson){
        System.out.println(objJson);
        DupResponse temp = new DupResponse();
        try{
            User user = userRepository.findByUserId(objJson);
            if(user != null){
                temp.setCode(0);
            }else{
                temp.setCode(1);
            }
        }catch (Exception e){
                temp.setCode(0);
        }
        return temp;
    }

    @RequestMapping(value = "/mypage/load", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User userInfoLoad(String objJson){
        System.out.println(objJson);
        User user = userRepository.findByUserId(objJson);
        return user;
    }

}
