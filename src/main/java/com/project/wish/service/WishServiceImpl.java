package com.project.wish.service;

import com.project.wish.domain.Wish;
import com.project.wish.dto.WishDto;
import com.project.wish.dto.WishUpdateDto;
import com.project.wish.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishServiceImpl implements WishService{

    private final WishRepository wishRepository;

    @Autowired
    public WishServiceImpl(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    @Override
    public void createWish(WishDto wishDto, String dateString, String registerDatetimeString, String modifyDatetimeString) throws ParseException {
        System.out.println("WishServiceImpl.createWish");
        System.out.println("wishDto = " + wishDto);
        wishRepository.insertWish(toEntity(wishDto, dateString, registerDatetimeString, modifyDatetimeString));
    }

    @Override
    public WishDto findWishById(Long id) {
        return toDto(wishRepository.findWishById(id));
    }

    @Override
    public void updateWish(Long id, WishUpdateDto wishUpdateDto) {
        Wish findWish = wishRepository.findWishById(id);
        WishDto updatedDto = dtoFromUpdateDto(toDto(findWish), wishUpdateDto);
//        wishRepository.updateWish(updateDtoToEntity(updatedDto));
    }

    @Override
    public void deleteWish(Long id) {
        wishRepository.deleteWish(id);
    }

    @Override
    public List<WishDto> findWishListByUserID(Long id) {
        return wishRepository.findWishListByUserID(id).stream()
                .map(wish -> toDto(wish))
                .collect(Collectors.toList());
    }

    @Override
    public WishDto findWishByWishId(Long wishId) {
        return toDto(wishRepository.findWishById(wishId));
    }

}
