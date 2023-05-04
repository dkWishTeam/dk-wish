package com.project.wish.service;

import com.project.wish.domain.Wish;
import com.project.wish.dto.WishDto;
import com.project.wish.dto.WishUpdateDto;


public interface WishService {

    void insertWish(WishDto wishDto);

    WishDto findWishById(Long id);

    void updateWish(Long id, WishUpdateDto wishUpdateDto);

    void deleteWish(Long id);

    default WishDto toDto(Wish wish) {
        return WishDto.builder()
                .id(wish.getId())
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

    default Wish toEntity(WishDto wishDTO) {
        return Wish.builder()
                .id(wishDTO.getId())
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

    default WishDto toDtoFromUpdateDto(WishDto wishDto, WishUpdateDto wishUpdateDto) {
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

}
