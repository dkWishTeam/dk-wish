package com.project.wish.controller;

import com.project.wish.dto.*;
import com.project.wish.dto.common.PageRequestDto;
import com.project.wish.service.UserService;
import com.project.wish.service.WishHistoryService;
import com.project.wish.service.WishService;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/wishes/{wishId}/wishHistories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class WishHistoryController {

    private final WishHistoryService wishHistoryService;
    private final WishService wishService;


    @GetMapping
    public WishHistoryInfoDto findWishHistoryInfoByWishId(@PathVariable Long wishId, PageRequestDto pageRequestDto) {
        WishHistoryInfoDto wishHistoryInfoDtos = new WishHistoryInfoDto();


        // user 닉네임 추가
        WishUserDto userInfo = wishHistoryService.getWishUserInfo(wishId);
        String wishTitle = wishService.findWishById(wishId).getTitle();
//        List<WishHistoryResponseDto> wishHistoryList = wishHistoryService.findWishHistoryListByWishId(wishId);
        WishHistoryRateDto wishHistoryRateDto = wishHistoryService.findRateByWishId(wishId);
        PageResponseHistoryListDto pageInfoList = wishHistoryService.findPageInfoList(wishId, pageRequestDto);

        wishHistoryInfoDtos.setWishUserDto(userInfo);
        wishHistoryInfoDtos.setTitle(wishTitle);
        wishHistoryInfoDtos.setWishHistoryRateDto(wishHistoryRateDto);
        wishHistoryInfoDtos.setPageResponseHistoryListDto(pageInfoList);
        wishHistoryInfoDtos.setMsg("아직 위시 기록이 없네요. 새로운 기록을 남겨보세요.");

        System.out.println();
        return wishHistoryInfoDtos;
    }

    @GetMapping("/page")
    public ResponseEntity<PageResponseHistoryListDto> findPagedWishHistory(@PathVariable Long wishId, PageRequestDto pageRequestDto) {
        PageResponseHistoryListDto pageInfoList = wishHistoryService.findPageInfoList(wishId, pageRequestDto);
        return ResponseEntity.ok(pageInfoList);
    }

    @PostMapping
    public ResponseEntity<String> createWishHistory(@RequestBody WishHistoryCreateDto wishHistoryCreateDto) {
        wishHistoryService.createWishHistory(wishHistoryCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("위시 히스토리가 기록 되었습니다.");
    }

    @RequestMapping(value = "/wishHistoryInfo/{id}")
    @ResponseBody
    public WishHistoryResponseDto findWishHistoryInfo(@PathVariable("id") Long id) {
        return wishHistoryService.findWishHistoryInfoById(id);
    }

    @PutMapping
    public ResponseEntity<?> updateWishHistory(@RequestBody WishHistoryUpdateRequestDto wishHistoryUpdateRequestDto) {
        wishHistoryService.updateWishHistory(wishHistoryUpdateRequestDto);
        System.out.println(wishHistoryUpdateRequestDto.toString());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{wishHistoryId}")
    @ResponseBody
    public Boolean deleteWishHistory(@PathVariable Long wishHistoryId) {
        return wishHistoryService.deleteWishHistory(wishHistoryId);
    }
}
