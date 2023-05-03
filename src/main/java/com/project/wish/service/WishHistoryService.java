package com.project.wish.service;

import com.project.wish.domain.WishHistory;
import com.project.wish.dto.WishHistoryCreateDto;
import com.project.wish.dto.WishHistoryResponseDto;
import com.project.wish.dto.WishHistoryUpdateRequestDto;
import java.time.LocalDateTime;
import java.util.List;

public interface WishHistoryService {

    List<WishHistoryResponseDto> findWishHistoryListByWishId(Integer wishId);

    WishHistoryResponseDto findWishHistoryOneById(Integer id);

    void insertWishHistory(WishHistory wishHistory);

    void updateWishHistory(WishHistory wishHistory);

    default WishHistoryResponseDto wishHistoryToWishHistoryResponseDto(WishHistory wishHistory) {
        return WishHistoryResponseDto.builder()
            .id(wishHistory.getId())
            .historyDatetime(wishHistory.getHistoryDatetime())
            .amount(wishHistory.getAmount())
            .build();
    }

    default WishHistory wishHistoryCreateDtoToWishHistory(WishHistoryCreateDto wishHistoryCreateDto, LocalDateTime localDateTime) {
        WishHistory wishHistory = WishHistory.builder()
            .id(wishHistoryCreateDto.getId())
            .wishId(wishHistoryCreateDto.getWishId())
            .historyDatetime(wishHistoryCreateDto.getHistoryDatetime())
            .amount(wishHistoryCreateDto.getAmount())
            .registerDatetime(wishHistoryCreateDto.getHistoryDatetime())
            .build();
        wishHistory.setModifyDatetime(localDateTime);
        return wishHistory;
    }

    default WishHistory wishUpdateRequestDtoToWishHistory(WishHistoryUpdateRequestDto wishHistoryUpdateRequestDto, Integer wishId, LocalDateTime localDateTime1, LocalDateTime localDateTime2){
        WishHistory wishHistory = WishHistory.builder()
            .id(wishHistoryUpdateRequestDto.getId())
            .historyDatetime(wishHistoryUpdateRequestDto.getHistoryDatetime())
            .amount(wishHistoryUpdateRequestDto.getAmount())
            .build();

        wishHistory.setRegisterDatetime(localDateTime1);
        wishHistory.setModifyDatetime(localDateTime2);;
        return wishHistory;
    }

//    default WishHistory wishHistoryCreateDtoToWishHistory(WishHistoryCreateDto dto) {
//
//    }


}


