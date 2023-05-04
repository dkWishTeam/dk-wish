package com.project.wish.wish;

import com.project.wish.domain.Wish;
import com.project.wish.dto.WishDto;
import com.project.wish.dto.WishUpdateDto;
import com.project.wish.repository.WishRepository;
import com.project.wish.service.WishService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest
@Transactional
@Rollback
public class WishTest {

    @Autowired
    WishRepository wishRepository;

    @Autowired
    WishService wishService;

    @Test
    @DisplayName("새로운 Wish 생성 테스트")
    void wishCreateTest() {

        Wish createdWish = Wish.builder()
                .id(1L)
                .userId(2L)
                .title("Wish create test")
                .content("content")
                .image("loopy.png")
                .productName("Macbook Pro 16 inch")
                .goalAmount(3500000L)
                .goalDate(new Date())
                .isPublic(true)
                .completionStatus(false)
                .registerDatetime(LocalDateTime.now())
                .modifyDatetime(LocalDateTime.now())
                .build();

        //given
        wishRepository.insertWish(createdWish);
        //when

        //then
        Assertions.assertThat(createdWish.getId()).isEqualTo(1L);
        Assertions.assertThat(createdWish.getImage()).isEqualTo("loopy.png");
    }

    @Test
    void wishFindTest() {
        //given
        Wish findWish = wishRepository.findWishById(1L);

        //when

        //when
        Assertions.assertThat(findWish.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Wish 수정 테스트")
    void updateWishTest() {

        //given
        WishDto beforeUpdate = wishService.findWishById(1L);

        WishUpdateDto wishUpdateDto = WishUpdateDto.builder()
                .title("Updated title")
                .content("Updated content")
                .image("Updated image")
                .productName("Updated product")
                .goalAmount(10L)
                .goalDate(new Date())
                .isPublic(true)
                .completionStatus(false)
                .modifyDatetime(LocalDateTime.now())
                .build();

        //when
        wishService.updateWish(beforeUpdate.getId(), wishUpdateDto);
        WishDto afterUpdateDto = wishService.findWishById(beforeUpdate.getId());

        //then
        Assertions.assertThat(beforeUpdate.getId()).isEqualTo(afterUpdateDto.getId());
        Assertions.assertThat(beforeUpdate.getContent()).isNotEqualTo(afterUpdateDto.getContent());
    }

    @Test
    @DisplayName("Wish 삭제 테스트")
    void deleteWishTest() {
        //given
        Wish createdWish = Wish.builder()
                .id(100L)
                .userId(2L)
                .title("Wish create test")
                .content("content")
                .image("loopy.png")
                .productName("Macbook Pro 16 inch")
                .goalAmount(3500000L)
                .goalDate(new Date())
                .isPublic(true)
                .completionStatus(false)
                .registerDatetime(LocalDateTime.now())
                .modifyDatetime(LocalDateTime.now())
                .build();

        //given
        wishRepository.insertWish(createdWish);

        //when
        wishRepository.deleteWish(createdWish.getId());

        //then
        Assertions.assertThat(wishRepository.findWishById(createdWish.getId())).isNull();
    }

}
