package com.project.wish.service;

import com.project.wish.domain.User;
import com.project.wish.domain.Wish;
import com.project.wish.dto.WishRequestDto;
import com.project.wish.dto.WishResponseDto;
import com.project.wish.dto.WishUpdateDto;

import java.time.LocalDateTime;
import java.util.List;


public interface WishService {


    void createWish(WishRequestDto wishRequestDto);

    WishResponseDto findWishById(Long id);


    void updateWish(WishUpdateDto wishUpdateDto);

    void deleteWish(Long id);

    List<WishResponseDto> findWishListByUserID(Long userId);

    default void updateDtoToEntity(Wish wish,WishUpdateDto wishUpdateDto){
        wish.setTitle(wishUpdateDto.getTitle());
        wish.setContent(wishUpdateDto.getContent());
        wish.setImage(wishUpdateDto.getImage());
        wish.setProductName(wishUpdateDto.getProductName());
        wish.setGoalAmount(wishUpdateDto.getGoalAmount());
        wish.setGoalDate(wishUpdateDto.getGoalDate());
        wish.setPublic(wishUpdateDto.isPublic());
    }

    default Wish wishRequestDtoToWish(WishRequestDto wishRequestDto, User user) {
        return Wish.builder()
                .id(wishRequestDto.getId())
                .user(user)
                .title(wishRequestDto.getTitle())
                .content(wishRequestDto.getContent())
                .image(wishRequestDto.getImage())
                .productName(wishRequestDto.getProductName())
                .goalAmount(wishRequestDto.getGoalAmount())
                .goalDate(wishRequestDto.getGoalDate())
                .isPublic(wishRequestDto.isPublic())
                .completionStatus(wishRequestDto.isCompletionStatus())
//                .registerDatetime(LocalDateTime.now())
//                .modifyDatetime(null)
                .build();
    }

    default WishResponseDto wishToWishResponseDto(Wish wish) {
        return WishResponseDto.builder()
                .id(wish.getId())
                .userId(wish.getUser().getId())
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

}
