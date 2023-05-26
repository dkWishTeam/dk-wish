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

    void createWish(WishDto wishDto);

    WishDto findWishById(Long id);


    void updateWish(WishUpdateDto wishUpdateDto);

    void deleteWish(Long id);

    List<WishDto> findWishListByUserID(Long id);

    default WishDto toDto(Wish wish) {
        return WishDto.builder()
                .id(wish.getId())
                .user(wish.getUser())
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

    default Wish toEntity(WishDto wishDTO) {
        return Wish.builder()
                .id(wishDTO.getId())
                .user(wishDTO.getUser())
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


    default Wish updateDtoToEntity(Wish wish,WishUpdateDto wishUpdateDto){
        wish.setTitle(wishUpdateDto.getTitle());
        wish.setContent(wishUpdateDto.getContent());
        wish.setImage(wishUpdateDto.getImage());
        wish.setProductName(wishUpdateDto.getProductName());
        wish.setGoalAmount(wishUpdateDto.getGoalAmount());
        wish.setGoalDate(wishUpdateDto.getGoalDate());
        wish.setPublic(wishUpdateDto.isPublic());
        return wish;
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

    String findTitleByWishId(Long wishId);
}
