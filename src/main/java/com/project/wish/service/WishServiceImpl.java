package com.project.wish.service;

import com.project.wish.domain.Wish;
import com.project.wish.dto.WishDto;
import com.project.wish.dto.WishUpdateDto;
import com.project.wish.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishServiceImpl implements WishService{

    private final WishRepository wishRepository;

    @Autowired
    public WishServiceImpl(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    @Override
    public void insertWish(WishDto wishDto) {
        wishRepository.insertWish(toEntity(wishDto));
    }

    @Override
    public WishDto findWishById(Long id) {
        return toDto(wishRepository.findWishById(id));
    }

    @Override
    public void updateWish(Long id, WishUpdateDto wishUpdateDto) {
        Wish findWish = wishRepository.findWishById(id);
        WishDto updatedDto = toDtoFromUpdateDto(toDto(findWish), wishUpdateDto);
        wishRepository.updateWish(toEntity(updatedDto));
    }

    @Override
    public void deleteWish(Long id) {
        wishRepository.deleteWish(id);
    }
}
