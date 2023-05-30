package com.project.wish.service;

import com.project.wish.domain.User;
import com.project.wish.domain.Wish;
import com.project.wish.domain.WishHistory;
import com.project.wish.dto.*;
import com.project.wish.enums.CheerUpPhrase;
import com.project.wish.repository.WishHistoryRepository;
import com.project.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishHistoryServiceImpl implements WishHistoryService {

    private final WishHistoryRepository wishHistoryRepository;
    private final WishRepository wishRepository;

    @Override
    public List<WishHistoryResponseDto> findWishHistoryListByWishId(Long wishId) {
        return wishRepository.findById(wishId).orElseThrow()
            .getWishHistories().stream()
            .map(this::wishHistoryToWishHistoryResponseDto).collect(Collectors.toList());
    }

    @Override
    public WishHistoryResponseDto findWishHistoryInfoById(Long id) {
        WishHistory wishHistoryById = wishHistoryRepository.findById(id).orElseThrow();
        return wishHistoryToWishHistoryResponseDto(wishHistoryById);
    }

    @Override
    public WishHistoryRateDto findRateByWishId(Long wishId) {

        Wish wish = wishRepository.findById(wishId).orElseThrow();
        List<WishHistory> wishHistories = wish.getWishHistories();
        Long percent = (wishHistories.stream().mapToLong(WishHistory::getAmount).sum() * 100)/wish.getGoalAmount();

        return WishHistoryRateDto.builder()
                .wishId(wishId)
                .percent(percent)
                .cheerUpPhrase(CheerUpPhrase.getByPercent(percent.intValue()).getMessage())
                .build();
    }

    @Override
    @Transactional
    public void createWishHistory(WishHistoryCreateDto wishHistoryCreateDto) {
        Wish wish = wishRepository.findById(wishHistoryCreateDto.getWishId()).orElseThrow();
        WishHistory wishHistory = wishHistoryCreateDtoToWishHistory(wishHistoryCreateDto, wish);
        wishHistoryRepository.save(wishHistory);
    }

    @Override
    @Transactional
    public void updateWishHistory(WishHistoryUpdateRequestDto wishHistoryUpdateRequestDto) {
        Wish wish = wishRepository.findById(wishHistoryUpdateRequestDto.getWishId()).orElseThrow();
        WishHistory wishHistory = wishHistoryRepository.findById(wishHistoryUpdateRequestDto.getWishId()).orElseThrow();
        wishHistory.setWish(wish);
        wishHistory.setModifyDatetime(LocalDateTime.now());
        wishHistory.setAmount(wishHistoryUpdateRequestDto.getAmount());
        wishHistory.setHistoryDatetime(wishHistory.getHistoryDatetime());
        wishHistoryRepository.save(wishHistory);
    }

    @Override
    @Transactional
    public Boolean deleteWishHistory(Long id) {
        wishHistoryRepository.deleteById(id);
        return true;
    }

    @Override
    public WishUserDto getWishUserInfo(Long wishId) {
        User user = wishRepository.findById(wishId).orElseThrow().getUser();
        return WishUserDto.builder()
            .userId(user.getId())
            .nickname(user.getNickname())
            .build();
    }
}