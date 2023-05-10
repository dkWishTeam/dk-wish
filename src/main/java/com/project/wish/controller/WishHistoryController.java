package com.project.wish.controller;

import com.project.wish.dto.*;
import com.project.wish.service.UserService;
import com.project.wish.service.WishHistoryService;
import com.project.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/wishHistory")
public class WishHistoryController {

    private final WishHistoryService wishHistoryService;
    private final WishService wishService;
    private final UserService userService;

    @GetMapping("/{wishId}")
    public String findWishHistoryListByWishId(@PathVariable Long wishId, Model model, HttpSession session) {
        if (!userService.loginMaintain(session)) return "redirect:/";

        // user 닉네임 추가
        WishUserDto userInfo = wishHistoryService.getWishUserInfo(wishId);
        model.addAttribute("wishUserDto", userInfo);

        WishDto wishDto = wishService.findWishByWishId(wishId);

        model.addAttribute("wishDto", wishDto);

        List<WishHistoryResponseDto> wishHistoryList = wishHistoryService.findWishHistoryListByWishId(wishId);
        model.addAttribute("wishId", wishId);

        model.addAttribute("dto", new WishHistoryUpdateRequestDto());

        WishHistoryRateDto wishHistoryRateDto = wishHistoryService.findRateByWishId(wishId);
        model.addAttribute("rate", wishHistoryRateDto);

        if (wishHistoryList.size() != 0) {
            model.addAttribute("wishHistoryResponseDtoList", wishHistoryList);
        } else {
            model.addAttribute("msg", "아직 위시 기록이 없네요. 새로운 기록을 남겨보세요.");
        }
        wishHistoryList.stream().forEach(System.out::println);
        return "wishHistory";
    }

//    @GetMapping("/{wishId}")
//    public String findTotalRateByWishId(@PathVariable Long wishId, Model model) {
//        Long totalAmount = wishHistoryService.findTotalRateByWishId(wishId);
//        model.addAttribute("totalAmount", totalAmount);
//        if()
//        return "wishHistory";
//    }

    @PostMapping(value = "/create")
    public String createWishHistory(WishHistoryCreateDto wishHistoryCreateDto, HttpSession session) {
        if (!userService.loginMaintain(session)) return "redirect:/";
        wishHistoryService.createWishHistory(wishHistoryCreateDto);
        return "redirect:/wishHistory/" + wishHistoryCreateDto.getWishId();
    }

    @RequestMapping(value = "/wishHistoryInfo/{id}")
    @ResponseBody
    public WishHistoryResponseDto findWishHistoryInfo(@PathVariable("id") Long id) {
        return wishHistoryService.findWishHistoryInfoById(id);
    }

    @PostMapping(value = "/update")
    public String updateWishHistory(WishHistoryUpdateRequestDto wishHistoryUpdateRequestDto, HttpSession session) {
        if (!userService.loginMaintain(session)) return "redirect:/";
        wishHistoryService.updateWishHistory(wishHistoryUpdateRequestDto);
        return "redirect:/wishHistory/" + wishHistoryUpdateRequestDto.getWishId();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public boolean deleteWishHistory(@PathVariable Long id) {
        return wishHistoryService.deleteWishHistory(id);
    }
}
