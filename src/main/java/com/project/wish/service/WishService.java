package com.project.wish.service;

import com.project.wish.domain.Wish;
import com.project.wish.dto.WishDto;
import com.project.wish.dto.WishUpdateDto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


public interface WishService {

    void createWish(WishDto wishDto, String dateString, String registerDatetimeString, String modifyDatetimeString) throws ParseException;

    WishDto findWishById(Long id);

    void updateWish(Long id, WishUpdateDto wishUpdateDto);

    void deleteWish(Long id);

    List<WishDto> findWishListByUserID(Long id);

    default WishDto toDto(Wish wish) {
        return WishDto.builder()
                .userId(wish.getUserId())
                .title(wish.getTitle())
                .content(wish.getContent())
                .image(wish.getImage())
                .productName(wish.getProductName())
                .goalAmount(wish.getGoalAmount())
                .goalDate(wish.getGoalDate())
                .isPublic(wish.isPublic())
                .completionStatus(wish.isCompletionStatus())
                .registerDatetime(wish.getRegisterDatetime())
                .modifyDatetime(wish.getModifyDatetime())
                .build();
    }

    default Wish toEntity(WishDto wishDTO, String dateString, String registerDatetimeString, String modifyDatetimeString) throws ParseException {
        return Wish.builder()
                .userId(wishDTO.getUserId())
                .title(wishDTO.getTitle())
                .content(wishDTO.getContent())
                .image(wishDTO.getImage())
                .productName(wishDTO.getProductName())
                .goalAmount(wishDTO.getGoalAmount())
                .goalDate((java.sql.Date) convertStringToDate(dateString))
                .isPublic(wishDTO.isPublic())
                .completionStatus(wishDTO.isCompletionStatus())
                .registerDatetime(convertStringToLocalDateTime(registerDatetimeString))
                .modifyDatetime(convertStringToLocalDateTime(modifyDatetimeString))
                .build();
    }

    default Wish updateDtoToEntity(WishDto wishDTO, String modifyDatetimeString) {
        return Wish.builder()
//                .id(wishDTO.getId())
                .userId(wishDTO.getUserId())
                .title(wishDTO.getTitle())
                .content(wishDTO.getContent())
                .image(wishDTO.getImage())
                .productName(wishDTO.getProductName())
                .goalAmount(wishDTO.getGoalAmount())
                .goalDate(wishDTO.getGoalDate())
                .isPublic(wishDTO.isPublic())
                .completionStatus(wishDTO.isCompletionStatus())
                .registerDatetime(wishDTO.getRegisterDatetime())
                .modifyDatetime(wishDTO.getModifyDatetime())
                .build();
    }


    default WishDto dtoFromUpdateDto(WishDto wishDto, WishUpdateDto wishUpdateDto) {
        wishDto.setTitle(wishUpdateDto.getTitle());
        wishDto.setContent(wishUpdateDto.getContent());
        wishDto.setImage(wishUpdateDto.getImage());
        wishDto.setProductName(wishUpdateDto.getProductName());
        wishDto.setGoalAmount(wishUpdateDto.getGoalAmount());
        wishDto.setGoalDate(wishUpdateDto.getGoalDate());
        wishDto.setPublic(wishUpdateDto.isPublic());
        wishDto.setCompletionStatus(wishDto.isCompletionStatus());
        wishDto.setModifyDatetime(wishUpdateDto.getModifyDatetime());
        return wishDto;
    }

    default Date convertStringToDate(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        return new java.sql.Date(date.getTime());
    }

    default LocalDateTime convertStringToLocalDateTime(String localdateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(localdateString, formatter);
    }

}
