package com.project.wish.service;

import com.project.wish.dto.WishListDto;
import java.util.List;

public interface WishListService {
    public List<WishListDto> getAllWishList();
    public List<WishListDto> getWishList(String path);
}
