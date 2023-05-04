package com.project.wish.service;

import com.project.wish.dto.WishPlaceDto;
import java.util.List;

public interface WishPlaceService {
    public List<WishPlaceDto> getWishPlace(String path);

}
