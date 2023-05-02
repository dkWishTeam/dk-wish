package com.project.wish.service;

import com.project.wish.dto.WishListDto;
import com.project.wish.repository.WishListRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListServiceImpl implements WishListService{
    @Autowired
    WishListRepository repo;

    @Override
    public List<WishListDto> getAllWishList() {
        List<WishListDto> list = repo.getAllWishList();
        //setNickNamePercent(list);
        return list;
    }

    @Override
    public List<WishListDto> getWishList(String path) {
        List<WishListDto> list = null;
        switch (path) {
            case "all" :
                list = getAllWishList();
                break;
            case "completion" :
                list = repo.getCompletionWishList();
                break;
            case "ongoing" :
                list = repo.getOngoingWishList();
                break;
            case "new" :
                list = repo.getNewWishList();
                break;
            default:
                break;
        }

        //setNickNamePercent(list);
        return list;
    }

    @Override
    public void setNickNamePercent(List<WishListDto> list) {
        for(WishListDto wish : list) {
            wish.setNickname(repo.getNickName(wish));
            //wish.setPercentage_completed(repo.getPercent(wish));
        }
    }


}
