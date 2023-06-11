package com.project.wish.service;

import com.project.wish.domain.Wish;
import com.project.wish.dto.WishRequestDto;
import com.project.wish.dto.WishResponseDto;
import com.project.wish.dto.WishUpdateDto;
import com.project.wish.repository.UserRepository;
import com.project.wish.repository.WishRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WishServiceImpl implements WishService {

    private final UserRepository userRepository;
    private final WishRepository wishRepository;

    public WishServiceImpl(UserRepository userRepository, WishRepository wishRepository) {
        this.userRepository = userRepository;
        this.wishRepository = wishRepository;
    }

    @Transactional
    @Override
    public Long createWish(WishRequestDto wishRequestDto){
        return wishRepository.save(
                wishRequestDtoToWish(wishRequestDto, userRepository.findById(wishRequestDto.getUserId())
                        .orElseThrow())).getId();
    }

    @Override
    public WishResponseDto findWishById(Long id) {
        return wishToWishResponseDto(wishRepository.findById(id).orElseThrow());
    }
    //todo
    @Transactional
    @Override
    public void updateWish(WishUpdateDto wishUpdateDto) {
        Wish findWish = wishRepository.findById(wishUpdateDto.getId()).orElseThrow();
        if (wishUpdateDto.getImage() == null) {
            wishUpdateDto.setImage(findWish.getImage());
        }
        updateDtoToEntity(findWish, wishUpdateDto);
    }

    @Override
    public void deleteWish(Long id) {
        Wish findWish = wishRepository.findById(id).orElseThrow();
        wishRepository.delete(findWish);
    }

    @Override
    public List<WishResponseDto> findWishListByUserID(Long userId) {
        List<Wish> wishListByUserID = wishRepository.findWishListByUserID(userRepository.findById(userId).orElseThrow());
        return wishListByUserID.stream().map(wish -> wishToWishResponseDto(wish)).collect(Collectors.toList());
    }

    @Override
    public void createLocalImageFolder(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                log.info("wish image temp dir created");
            } else {
                log.info("creating wish image temp dir failed");
            }
        }
    }

}
