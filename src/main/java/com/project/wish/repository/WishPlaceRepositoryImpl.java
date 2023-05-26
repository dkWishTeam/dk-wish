package com.project.wish.repository;

import com.project.wish.dto.WishPlaceDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class WishPlaceRepositoryImpl implements WishPlaceRepository {

    @Override
    public List<WishPlaceDto> findAllWishPlaceList() {
        return null;
    }

    @Override
    public List<WishPlaceDto> findCompletionWishPlaceList() {
        return null;
    }

    @Override
    public List<WishPlaceDto> findOngoingWishPlaceList() {
        return null;
    }

    @Override
    public List<WishPlaceDto> findNewWishPlaceList() {
        return null;
    }
}
