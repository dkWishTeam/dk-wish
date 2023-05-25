package com.project.wish.service;

import com.project.wish.domain.Wish;
import com.project.wish.dto.WishDto;
import com.project.wish.dto.WishUpdateDto;
import com.project.wish.repository.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;

    public WishServiceImpl(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    @Override
    public void createWish(WishDto wishDto){
        wishRepository.save(toEntity(wishDto));
    }

    @Override
    public WishDto findWishById(Long id) {
        return toDto(wishRepository.findById(id).orElseThrow());
    }
    @Transactional
    @Override
    public void updateWish(WishUpdateDto wishUpdateDto) {
        Wish findWish = wishRepository.findById(wishUpdateDto.getId()).orElseThrow();
        updateDtoToEntity(findWish, wishUpdateDto);
    }

    @Override
    public void deleteWish(Long id) {
        Wish findWish = wishRepository.findById(id).orElseThrow();
        wishRepository.delete(findWish);
    }

    @Override
    public List<WishDto> findWishListByUserID(Long id) {
        return null;
    }

}
