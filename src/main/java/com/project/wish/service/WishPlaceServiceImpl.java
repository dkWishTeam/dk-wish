package com.project.wish.service;

import com.project.wish.dto.WishPlaceDto;
import com.project.wish.repository.WishPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishPlaceServiceImpl implements WishPlaceService {
    @Autowired
    WishPlaceRepository repo;

    @Override
    public List<WishPlaceDto> getWishPlace(String path) {
        List<WishPlaceDto> list = null;
        switch (path) {
            case "all" :
                list = repo.findAllWishPlaceList();
                break;
            case "completion" :
                list = repo.findCompletionWishPlaceList();
                break;
            case "ongoing" :
                list = repo.findOngoingWishPlaceList();
                break;
            case "new" :
                list = repo.findNewWishPlaceList();
                break;
            default:
                break;
        }
        setWishImage(list);
        return list;
    }

    @Override
    public void setWishImage(List<WishPlaceDto> list) {
        String temp;
        for(WishPlaceDto l : list) {
            if(l.getImage() == null || l.getImage().indexOf("/uploadImages") == -1) {
                l.setImageSrc("/images/default.png");
            } else {
                temp = l.getImage().substring(l.getImage().indexOf("/uploadImages"));
                l.setImageSrc(temp);
            }
        }
    }
}
