package com.project.wish.controller;

import com.project.wish.dto.*;
import com.project.wish.service.UserService;
import com.project.wish.service.WishHistoryService;
import com.project.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/wishes/{wishId}/wishHistories")
@Slf4j
public class WishHistoryController {
    private final WishHistoryService wishHistoryService;
    private final WishService wishService;
    private final UserService userService;


    @GetMapping
    public String findWishHistoryListByWishId(@PathVariable Long wishId, Model model, HttpSession session) {
        session.setAttribute("wishId", wishId);
        if (!userService.isLogin(session)) return "redirect:/";

        // user 닉네임 추가
        WishUserDto userInfo = wishHistoryService.getWishUserInfo(wishId);

        model.addAttribute("wishUserDto", userInfo);

        model.addAttribute("title", wishService.findWishById(wishId));

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
        //log.info("================== {} 위시 조화 ==================", wishId);
        return "wishHistory";
    }

    //"/wishes/{wishId}/wishHistories"
    @PostMapping
    public String createWishHistory(WishHistoryCreateDto wishHistoryCreateDto, HttpSession session) {
        if (!userService.isLogin(session)) return "redirect:/";
        wishHistoryService.createWishHistory(wishHistoryCreateDto);
        return "redirect:/wishes/" + wishHistoryCreateDto.getWishId() + "/wishHistories";
    }

    @RequestMapping(value = "/wishHistoryInfo/{id}")
    @ResponseBody
    public WishHistoryResponseDto findWishHistoryInfo(@PathVariable("id") Long id) {
        return wishHistoryService.findWishHistoryInfoById(id);
    }

    @PutMapping
    public String updateWishHistory(WishHistoryUpdateRequestDto wishHistoryUpdateRequestDto, HttpSession session) {
        if (!userService.isLogin(session)) return "redirect:/";
        wishHistoryService.updateWishHistory(wishHistoryUpdateRequestDto);
//        return "redirect:/wishHistory/" + wishHistoryUpdateRequestDto.getWishId();
        return "redirect:/wishes/" + wishHistoryUpdateRequestDto.getWishId() + "/wishHistories";
    }


    @DeleteMapping("/{wishHistoryId}")
    @ResponseBody
    public Boolean deleteWishHistory(@PathVariable Long wishHistoryId) {
        return wishHistoryService.deleteWishHistory(wishHistoryId);


    }
}
