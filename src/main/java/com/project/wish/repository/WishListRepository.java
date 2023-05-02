package com.project.wish.repository;

import com.project.wish.dto.WishListDto;
import java.util.List;

public interface WishListRepository {
    public List<WishListDto> getAllWishList();
    public List<WishListDto> getCompletionWishList();
    public List<WishListDto> getOngoingWishList();
    public List<WishListDto> getNewWishList();

    public String getNickName(WishListDto dto);
    public WishListDto getPercent(WishListDto dto);
}
