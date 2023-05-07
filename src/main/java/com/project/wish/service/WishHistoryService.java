package com.project.wish.service;

import com.project.wish.domain.WishHistory;
import com.project.wish.dto.WishDto;
import com.project.wish.dto.WishHistoryCreateDto;
import com.project.wish.dto.WishHistoryRateDto;
import com.project.wish.dto.WishHistoryResponseDto;
import com.project.wish.dto.WishHistoryUpdateRequestDto;
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

    void deleteWishHistory(Long id);

    default WishHistoryResponseDto wishHistoryToWishHistoryResponseDto(WishHistory wishHistory) {
        return WishHistoryResponseDto.builder()
            .wishId(wishHistory.getWishId())
            .id(wishHistory.getId())
            .historyDatetime(wishHistory.getHistoryDatetime())
            .registerDatetime(wishHistory.getRegisterDatetime())
            .amount(wishHistory.getAmount())
            .build();
    }

    default WishHistory wishHistoryCreateDtoToWishHistory(WishHistoryCreateDto wishHistoryCreateDto) {
        return WishHistory.builder()
            .wishId(wishHistoryCreateDto.getWishId())
            .historyDatetime(convertSqlDateToLocalDateTime(wishHistoryCreateDto.getHistoryDatetime()))
            .amount(wishHistoryCreateDto.getAmount())
            .registerDatetime(LocalDateTime.now())
            .build();
    }

    default WishHistory wishHistoryUpdateRequestDtoToWishHistory(WishHistoryUpdateRequestDto wishHistoryUpdateRequestDto, LocalDateTime registerDatetime){
        return WishHistory.builder()
            .wishId(wishHistoryUpdateRequestDto.getWishId())
            .id(wishHistoryUpdateRequestDto.getId())
            .historyDatetime(convertSqlDateToLocalDateTime(wishHistoryUpdateRequestDto.getHistoryDatetime()))
            .amount(wishHistoryUpdateRequestDto.getAmount())
            .registerDatetime(registerDatetime)
            .modifyDatetime(LocalDateTime.now())
            .build();
    }

    default LocalDateTime convertSqlDateToLocalDateTime(Date sqlDate){
        java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
        LocalDateTime localDateTime = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime;
    }
//    default WishHistory wishHistoryCreateDtoToWishHistory(WishHistoryCreateDto dto) {
//v
//    }


}


