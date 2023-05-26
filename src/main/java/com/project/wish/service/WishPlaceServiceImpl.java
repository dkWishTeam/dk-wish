package com.project.wish.service;

import com.project.wish.dto.WishPlaceDto;
import com.project.wish.repository.WishPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishPlaceServiceImpl implements WishPlaceService {
    @Autowired
    WishPlaceRepository repo;

    @Override
    public List<WishPlaceDto> getWishPlace(String path) {
        List<WishPlaceDto> list = null;
        Pageable pageable = PageRequest.of(0, 9);

        switch (path) {
            case "all" :
                list = repo.findAllWishPlaceList(pageable);
                break;
            case "completion" :
                list = repo.findCompletionWishPlaceList(pageable);
                break;
            case "ongoing" :
                list = repo.findOngoingWishPlaceList(pageable);
                break;
            case "new" :
                list = repo.findNewWishPlaceList(pageable);
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
            if(l.getImageSrc() == null || l.getImageSrc().indexOf("/uploadImages") == -1) {
                l.setImageSrc("/images/default.png");
            } else {
                temp = l.getImageSrc().substring(l.getImageSrc().indexOf("/uploadImages"));
                l.setImageSrc(temp);
            }
        }
    }
}
