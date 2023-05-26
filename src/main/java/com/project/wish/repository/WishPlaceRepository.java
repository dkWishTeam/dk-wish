package com.project.wish.repository;

import com.project.wish.dto.WishPlaceDto;
import java.util.List;
import org.springframework.stereotype.Component;

public interface WishPlaceRepository {
    public List<WishPlaceDto> findAllWishPlaceList();
    public List<WishPlaceDto> findCompletionWishPlaceList();
    public List<WishPlaceDto> findOngoingWishPlaceList();
    public List<WishPlaceDto> findNewWishPlaceList();
}
