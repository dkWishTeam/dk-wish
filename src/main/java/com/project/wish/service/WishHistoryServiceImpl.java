package com.project.wish.service;

import com.example.simpleboardapi.common.utils.PagingUtil;
import com.project.wish.domain.User;
import com.project.wish.domain.Wish;
import com.project.wish.domain.WishHistory;
import com.project.wish.dto.*;
import com.project.wish.dto.common.PageRequestDto;
import com.project.wish.enums.CheerUpPhrase;
import com.project.wish.repository.WishHistoryRepository;
import com.project.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
        System.out.println(wish);
        WishHistory wishHistory = wishHistoryRepository.findById(wishHistoryUpdateRequestDto.getId()).orElseThrow();
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
    public PageResponseHistoryListDto findPageInfoList(Long wishId, PageRequestDto pageRequestDto) {
        PageRequest pageRequest = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getPageSize());
        Page<WishHistory> wishHistoryList = wishHistoryRepository.findAllByWish(wishId, pageRequest);     //페이징 처리가 된 엔티티를 받아올 수 있다.

        PageResponseHistoryListDto pageResponseHistoryListDto = PageResponseHistoryListDto.builder()
            .pagingUtil(new PagingUtil(wishHistoryList.getTotalElements(), wishHistoryList.getTotalPages(),
                wishHistoryList.getNumber(), wishHistoryList.getSize()))
            .wishHistoryList(wishHistoryList.stream().map(this::wishHistoryToWishHistoryResponseDto).collect(Collectors.toList()))
            .build();

        return pageResponseHistoryListDto;
    }

//    public List<WishHistoryResponseDto> findWishHistoryListByWishId(Long wishId) {
//        return wishRepository.findById(wishId).orElseThrow()
//            .getWishHistories().stream()
//            .map(this::wishHistoryToWishHistoryResponseDto).collect(Collectors.toList());
//    }


    @Override
    public WishUserDto getWishUserInfo(Long wishId) {
        User user = wishRepository.findById(wishId).orElseThrow().getUser();
        return WishUserDto.builder()
            .userId(user.getId())
            .nickname(user.getNickname())
            .build();
    }
}