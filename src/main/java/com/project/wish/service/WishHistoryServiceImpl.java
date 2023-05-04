package com.project.wish.service;

import com.project.wish.domain.WishHistory;
import com.project.wish.dto.WishHistoryCreateDto;
import com.project.wish.dto.WishHistoryResponseDto;
import com.project.wish.dto.WishHistoryUpdateRequestDto;
import com.project.wish.repository.WishHistoryRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishHistoryServiceImpl implements WishHistoryService{

    private final WishHistoryRepository wishHistoryRepository;

    @Override
    public List<WishHistoryResponseDto> findWishHistoryListByWishId(Long wishId) {
        List<WishHistory> wishHistoryListByWishId = wishHistoryRepository.findWishHistoryListByWishId(wishId);

        return wishHistoryListByWishId.stream().map(this::wishHistoryToWishHistoryResponseDto).collect(
            Collectors.toList());
    }

    public WishHistoryResponseDto findWishHistoryInfoById(Long id) {
        WishHistory wishHistoryById = wishHistoryRepository.findWishHistoryInfoById(id);
        return wishHistoryToWishHistoryResponseDto(wishHistoryById);
    }

    @Override
    public void createWishHistory(WishHistoryCreateDto wishHistoryCreateDto) {
        WishHistory wishHistory = wishHistoryCreateDtoToWishHistory(wishHistoryCreateDto);
        wishHistoryRepository.createWishHistory(wishHistory);
    }

    @Override
    public void updateWishHistory(WishHistoryUpdateRequestDto wishHistoryUpdateRequestDto) {
        WishHistory wishHistoryInfoById = wishHistoryRepository.findWishHistoryInfoById(
            wishHistoryUpdateRequestDto.getId());
        wishHistoryInfoById.setModifyDatetime(LocalDateTime.now());
        wishHistoryInfoById.setAmount(wishHistoryInfoById.getAmount());
        wishHistoryInfoById.setHistoryDatetime(wishHistoryInfoById.getHistoryDatetime());
        wishHistoryRepository.updateWishHistory(wishHistoryInfoById);
    }


    @Override
    public void deleteWishHistory(Long id) {
        wishHistoryRepository.deleteWishHistory(id);
    }
}
