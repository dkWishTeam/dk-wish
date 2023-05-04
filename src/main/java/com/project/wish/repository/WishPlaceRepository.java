package com.project.wish.repository;

import com.project.wish.dto.WishPlaceDto;
import java.util.List;

public interface WishPlaceRepository {
    public List<WishPlaceDto> getAllWishPlaceList();
    public List<WishPlaceDto> getCompletionWishPlaceList();
    public List<WishPlaceDto> getOngoingWishPlaceList();
    public List<WishPlaceDto> getNewWishPlaceList();
}
