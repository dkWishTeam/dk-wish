package com.project.wish.service;

import com.project.wish.domain.WishHistory;
import com.project.wish.dto.WishHistoryResponseDto;
import com.project.wish.repository.WishHistoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishHistoryServiceImpl implements WishHistoryService{

    private final WishHistoryRepository wishHistoryRepository;

    @Override
    public List<WishHistoryResponseDto> findWishHistoryListByWishId(Integer wishId) {
        List<WishHistory> wishHistoryListByWishId = wishHistoryRepository.findWishHistoryListByWishId(wishId);
        List<WishHistoryResponseDto> wishHistoryResponseDtoList = wishHistoryListByWishId.stream().map(e -> wishHistoryToWishHistoryResponseDto(e)).collect(
            Collectors.toList());
        return wishHistoryResponseDtoList;
    }

    @Override
    public WishHistoryResponseDto findWishHistoryOneById(Integer id) {
        WishHistory wishHistoryOneById = wishHistoryRepository.findWishHistoryOneById(id);
        WishHistoryResponseDto wishHistoryResponseDto = wishHistoryToWishHistoryResponseDto(wishHistoryOneById);
        return wishHistoryResponseDto;
    }

    @Override
    public void insertWishHistory(WishHistory wishHistory) {

    }

    @Override
    public void updateWishHistory(WishHistory wishHistory) {

    }
}
