package com.example.pupusa.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class MenuController {

    private final MenuService menuService;

    @RequestMapping(value = "menu/menuload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MenuResponse menuLoad() {

        MenuResponse temp = new MenuResponse();
        try{
            temp.setMenuList(menuService.getMenuList());
            return temp;
        } catch (Exception e){
            return temp;
        }
    }
}
