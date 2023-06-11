package com.project.wish.service;

import com.project.wish.domain.WishHistory;
import com.project.wish.repository.WishHistoryRepository;
import com.project.wish.repository.WishRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class WishHistoryServiceTest {

    @Autowired
    WishHistoryRepository wishHistoryRepository;
    @Autowired
    WishRepository wishRepository;

    @Test
    void wishHistoryUpdateTest() {
        //given
        WishHistory history = wishHistoryRepository.findAll().get(0);
        history.setAmount(2929L);

        //when


        //then

    }

}