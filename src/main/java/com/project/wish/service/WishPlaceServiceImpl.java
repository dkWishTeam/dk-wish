package com.project.wish.service;

import com.project.wish.dto.WishPlaceDto;
import com.project.wish.repository.WishPlaceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishPlaceServiceImpl implements WishPlaceService {
    @Autowired
    WishPlaceRepository repo;

    @Override
    public List<WishPlaceDto> getWishPlace(String path) {
        List<WishPlaceDto> list = null;
        switch (path) {
            case "all" :
                list = repo.getAllWishPlaceList();
                break;
            case "completion" :
                list = repo.getCompletionWishPlaceList();
                break;
            case "ongoing" :
                list = repo.getOngoingWishPlaceList();
                break;
            case "new" :
                list = repo.getNewWishPlaceList();
                break;
            default:
                break;
        }
        return list;
    }
}
