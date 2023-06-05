package com.project.wish.controller;

import com.project.wish.dto.*;
import com.project.wish.service.UserService;
import com.project.wish.service.WishHistoryService;
import com.project.wish.service.WishService;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/wishes/{wishId}/wishHistories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class WishHistoryController {

    private final WishHistoryService wishHistoryService;
    private final WishService wishService;
    private final UserService userService;


    @GetMapping
    public WishHistoryInfoDto findWishHistoryInfoByWishId(@PathVariable Long wishId, Model model, HttpSession session) {
        session.setAttribute("wishId", wishId);
//        if (!userService.isLogin(session)) return "redirect:/";
        WishHistoryInfoDto wishHistoryInfoDtos = new WishHistoryInfoDto();

        // user 닉네임 추가
        WishUserDto userInfo = wishHistoryService.getWishUserInfo(wishId);
        String wishTitle = wishService.findWishById(wishId).getTitle();
        WishHistoryRateDto wishHistoryRateDto = wishHistoryService.findRateByWishId(wishId);
        List<WishHistoryResponseDto> wishHistoryList = wishHistoryService.findWishHistoryListByWishId(wishId);

        wishHistoryInfoDtos.setWishUserDto(userInfo);
        wishHistoryInfoDtos.setTitle(wishTitle);
        wishHistoryInfoDtos.setWishHistoryRateDto(wishHistoryRateDto);

        wishHistoryInfoDtos.setMsg("아직 위시 기록이 없네요. 새로운 기록을 남겨보세요.");

        if (wishHistoryList.size() != 0) {
            wishHistoryInfoDtos.setWishHistoryList(wishHistoryList);
        }
        wishHistoryList.stream().forEach(System.out::println);

        return wishHistoryInfoDtos;
    }

    @PostMapping
    public ResponseEntity<String> createWishHistory(@RequestBody WishHistoryCreateDto wishHistoryCreateDto) {
//        if (!userService.isLogin(session)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
//        }

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
//        if (!userService.isLogin(session)) {
//            return "redirect:/";
//        }
        wishHistoryService.updateWishHistory(wishHistoryUpdateRequestDto);
        System.out.println(wishHistoryUpdateRequestDto.toString());
//        return "redirect:/wishHistory/" + wishHistoryUpdateRequestDto.getWishId();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{wishHistoryId}")
    @ResponseBody
    public Boolean deleteWishHistory(@PathVariable Long wishHistoryId) {
        return wishHistoryService.deleteWishHistory(wishHistoryId);

    }
}
