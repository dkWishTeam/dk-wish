package com.project.wish.service;

import com.project.wish.dto.WishPlaceDto;

import java.util.List;

public interface WishPlaceService {
    public List<WishPlaceDto> getWishPlace(String path, Integer start, Integer size);

    public List<WishPlaceDto> getSearchWishPlace(String search, Integer start, Integer size);

    public int getWishCount(String path);

    public void setWishImage(List<WishPlaceDto> list);
}
