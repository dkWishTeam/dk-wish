package com.project.wish.service;

import com.project.wish.domain.Wish;
import com.project.wish.repository.WishHistoryRepository;
import com.project.wish.repository.WishRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class WishRateTest {

    @Autowired
    WishHistoryRepository wishHistoryRepository;

    @Autowired
    WishRepository wishRepository;

    @Test
    void rateTest() {
        //given
        Wish findWish = wishRepository.findById(120L).get();
        System.out.println(findWish);
        System.out.println(findWish.getWishHistories());

        //when


        //then

    }

}
