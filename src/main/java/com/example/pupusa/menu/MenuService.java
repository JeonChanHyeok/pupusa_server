package com.example.pupusa.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service

public class MenuService {
    private final MenuRepository menuRepository;

    @Transactional
    public List<Menu> getMenuList(){
        List<Menu> temp = menuRepository.findAll();
        return temp;
    }
}
