package com.project.wish.service;

import static org.junit.jupiter.api.Assertions.*;

import com.project.wish.domain.WishHistory;
import com.project.wish.dto.PageResponseHistoryListDto;
import com.project.wish.dto.common.PageRequestDto;
import com.project.wish.repository.WishHistoryRepository;
import com.project.wish.repository.WishRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class WishHistoryServiceImplTest {

    @Autowired
    WishHistoryRepository wishHistoryRepository;

    @Autowired
    WishRepository wishRepository;


    @Test
    void findAllTest() {
        PageRequestDto pageRequestDto = new PageRequestDto(0, 10);
            PageRequest pageRequest = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getPageSize());

        Page<WishHistory> allByWish = wishHistoryRepository.findAllByWish(120L,
            pageRequest);

        for (WishHistory wishHistory : allByWish) {
            System.out.println(wishHistory);
        }
    }

}