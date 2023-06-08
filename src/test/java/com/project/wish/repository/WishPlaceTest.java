package com.project.wish.repository;

import com.project.wish.dto.WishPlaceDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback
class WishPlaceTest {

    @Autowired
    WishPlaceRepository wishPlaceRepository;

    @Test
    void findAllWishes() {
        Pageable pageable = PageRequest.of(0, 4);

        List<WishPlaceDto> wishPlaceDto = wishPlaceRepository.findAllWishPlaceList(pageable);
        wishPlaceDto.stream().forEach(System.out::println);

        List<WishPlaceDto> wishPlaceDto2 = wishPlaceRepository.findAllWishPlaceList();
        System.out.println(wishPlaceDto2.size());
    }

    @Test
    void findCompleteWishes() {
        Pageable pageable = PageRequest.of(0, 9);

        List<WishPlaceDto> wishPlaceDto = wishPlaceRepository.findCompletionWishPlaceList(pageable);
        wishPlaceDto.stream().forEach(System.out::println);
    }

    @Test
    void findNotCompleteWishes() {
        Pageable pageable = PageRequest.of(0, 9);

        List<WishPlaceDto> wishPlaceDto = wishPlaceRepository.findOngoingWishPlaceList(pageable);
        wishPlaceDto.stream().forEach(System.out::println);
    }

    @Test
    void findNewWishes() {
        Pageable pageable = PageRequest.of(0, 9);

        List<WishPlaceDto> wishPlaceDto = wishPlaceRepository.findNewWishPlaceList(pageable);
        wishPlaceDto.stream().forEach(System.out::println);
    }

    @Test
    void searchWishes() {
        Pageable pageable = PageRequest.of(0, 9);

        List<WishPlaceDto> wishPlaceDto = wishPlaceRepository.findSearchWishPlaceList("시그니엘", pageable);
        wishPlaceDto.stream().forEach(System.out::println);
    }
}
