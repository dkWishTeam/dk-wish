package com.project.wish.service;

import com.project.wish.domain.Wish;
import com.project.wish.domain.WishHistory;
import com.project.wish.dto.*;

import com.project.wish.dto.common.PageRequestDto;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public interface WishHistoryService {

    List<WishHistoryResponseDto> findWishHistoryListByWishId(Long wishId);

    WishHistoryResponseDto findWishHistoryInfoById(Long id);

    WishHistoryRateDto findRateByWishId(Long wishId);

    void createWishHistory(WishHistoryCreateDto wishHistoryCreateDto);

    void updateWishHistory(WishHistoryUpdateRequestDto wishHistoryUpdateRequestDto);

    Boolean deleteWishHistory(Long id);

    PageResponseHistoryListDto findPageInfoList(Long wishId, PageRequestDto pageRequestDto);

    default WishHistoryResponseDto wishHistoryToWishHistoryResponseDto(WishHistory wishHistory) {
        return WishHistoryResponseDto.builder()
            .wishId(wishHistory.getWish().getId())
            .id(wishHistory.getId())
            .historyDatetime(wishHistory.getHistoryDatetime())
            .registerDatetime(wishHistory.getRegisterDatetime())
            .amount(wishHistory.getAmount())
            .build();
    }

    default WishHistory wishHistoryCreateDtoToWishHistory(WishHistoryCreateDto wishHistoryCreateDto, Wish wish) {
        return WishHistory.builder()
            .wish(wish)
            .historyDatetime(convertSqlDateToLocalDateTime(wishHistoryCreateDto.getHistoryDatetime()))
            .amount(wishHistoryCreateDto.getAmount())
            .build();
    }

    default LocalDateTime convertSqlDateToLocalDateTime(Date sqlDate){
        java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
        LocalDateTime localDateTime = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime;
    }

    WishUserDto getWishUserInfo(Long wishId);

}