package com.project.wish.repository;

import com.project.wish.domain.User;
import com.project.wish.domain.Wish;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
class WishRepositoryTest {

    @Autowired
    WishRepository wishRepository;

    @Autowired
    UserRepository userRepository;

    static User user;
    static Long userId;

    @Test
    @DisplayName("전체 위시 조회")
    void findAllWishes() {
        //given
        List<Wish> wishList = wishRepository.findAll();
        for (Wish wish : wishList) {
            System.out.println(wish);
        }
    }

    @Test
    void createWishTest() {
        userId = userRepository.findAll().get(0).getId();
        user = userRepository.findById(userId).orElseThrow();

        //given
        Wish newWish = Wish.builder()
                .id(0L)
                .user(user)
                .title("test title")
                .content("test content")
                .image("test img")
                .productName("test product")
                .goalAmount(10000L)
                .goalDate(new Date(System.currentTimeMillis()))
                .isPublic(false)
                .completionStatus(false)
                .registerDatetime(LocalDateTime.now())
                .modifyDatetime(LocalDateTime.now())
                .build();

        //when
        int beforeSave = wishRepository.findAll().size();
        wishRepository.save(newWish);
        int afterSave = wishRepository.findAll().size();

        System.out.println("newWish = " + newWish);
        //then
        assertThat(beforeSave + 1).isEqualTo(afterSave);
    }

    @Test
    void updateWishTest() {

        //given
        List<Wish> wishList = wishRepository.findAll();
        Long wishId = wishList.get(1).getId();
        Wish findWish = wishRepository.findById(wishId).get();
        System.out.println("수정하기전 제목 : " + findWish.getTitle());

        //when
        String newTitle = "wish title updated";
        findWish.setTitle(newTitle);
        System.out.println("수정 후 제목 : " + findWish.getTitle());
        wishRepository.save(findWish);

        //then
        assertThat(wishRepository.findById(wishId).get().getTitle()).isEqualTo(newTitle);
    }

    @Test
    void deleteWishTest() {

        userId = userRepository.findAll().get(0).getId();
        user = userRepository.findById(userId).orElseThrow();

        //given
        Wish newWish = Wish.builder()
                .id(0L)
                .user(user)
                .title("test title")
                .content("test content")
                .image("test img")
                .productName("test product")
                .goalAmount(10000L)
                .goalDate(new Date(System.currentTimeMillis()))
                .isPublic(false)
                .completionStatus(false)
                .registerDatetime(LocalDateTime.now())
                .modifyDatetime(LocalDateTime.now())
                .build();
        //when
        Long id = wishRepository.save(newWish).getId();
        System.out.println("id = " + id);
        wishRepository.delete(wishRepository.findById(id).get());

        //then
        assertThat(wishRepository.findById(id).orElse(null)).isEqualTo(null);
    }

    @Test
    void findWishByUserID() {
        //given
        User findUser = userRepository.findById(1L).get();
        List<Wish> wishList = wishRepository.findWishListByUserID(findUser);

        //when


        //then
        for (Wish wish : wishList) {
            System.out.println(wish.getUser().getId() + " =? " + findUser.getId());
            assertThat(wish.getUser().getId()).isEqualTo(findUser.getId());
        }
    }
}