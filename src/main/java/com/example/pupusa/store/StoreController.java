package com.example.pupusa.store;

import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final StoreRepository storeRepository;

    @RequestMapping(value = "/store/load_store_info", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public StoreResponseList storeLoad(String objJson) {
        // 카테고리로 식당 출력하기
        System.out.println("Store access..");
        System.out.println(objJson);

        StoreResponseList storeResponseList = new StoreResponseList();

        storeResponseList.setStoreResponseList(null);
        // 식당 리스트 초기화

        List<StoreResponse> storeResponseLists = new ArrayList<>();

        StoreResponse storeResponse = null;

        if (objJson.equals("전체")){

            for (Store s : storeRepository.findAll()) {
                System.out.println("for");
                storeResponse = new StoreResponse();
                storeResponse.setStoreId(s.getStoreId());
                storeResponse.setStoreName(s.getStoreName());
                storeResponse.setCategory(s.getCategory());
                storeResponseLists.add(storeResponse);
                //System.out.println(storeResponse.getStoreName());
            }
        }
        else{

            for (Store s : storeRepository.findAllByCategory(objJson)) {
                System.out.println("for");
                storeResponse = new StoreResponse();
                storeResponse.setStoreId(s.getStoreId());
                storeResponse.setStoreName(s.getStoreName());
                storeResponse.setCategory(s.getCategory());
                storeResponseLists.add(storeResponse);
                //System.out.println(storeResponse.getStoreName());
            }
        }

        storeResponseList.setStoreResponseList(storeResponseLists);
        System.out.println(storeResponseLists);
        return storeResponseList;
    }
}

