package com.example.pupusa.user;

import com.example.pupusa.Response.DupResponse;
import com.example.pupusa.Response.JoinResponse;
import com.example.pupusa.Response.LoginResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;


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
            UserRequestDto dto = gson.fromJson(objJson, UserRequestDto.class);
            User user = new User(dto);
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
        Gson gson = new Gson();
        LoginResponse temp = new LoginResponse();
        try{
            UserRequestDto dto = gson.fromJson(objJson, UserRequestDto.class);
            User user = new User(dto);
            if(userService.Login(user)){
                temp.setCode(1);
                temp.setMessage("로그인 성공");
            }else{
                temp.setCode(2);
                temp.setMessage("로그인 실패");
            }
            return temp;
        }catch (Exception e){
            e.printStackTrace();
            temp.setCode(2);
            temp.setMessage("로그인 실패");
            return temp;
        }
    }

    @RequestMapping(value = "/user/joindupchk", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DupResponse dupCheck(String email){
        DupResponse temp = new DupResponse();
        try{
            User user = userRepository.findByUserId(email);
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
}
