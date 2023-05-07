package com.project.wish.controller;

import com.project.wish.domain.WishHistory;
import com.project.wish.dto.WishDto;
import com.project.wish.dto.WishHistoryCreateDto;
import com.project.wish.dto.WishHistoryRateDto;
import com.project.wish.dto.WishHistoryResponseDto;
import com.project.wish.dto.WishHistoryUpdateRequestDto;
import com.project.wish.repository.WishHistoryRepositoryImpl;
import com.project.wish.service.WishHistoryService;
import com.project.wish.service.WishService;
import com.sun.tools.jconsole.JConsoleContext;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/wishHistory")
public class WishHistoryController {

    private final WishHistoryService wishHistoryService;
    private final WishService wishService;

    @GetMapping("/{wishId}")
    public String findWishHistoryListByWishId(@PathVariable Long wishId, Model model) {
        // 회원 user_id, title 보여줌
        WishDto wishDto = wishService.findWishByWishId(wishId);
        model.addAttribute("wishDto", wishDto);

        // 회원 기록 리스트 보여줌
        List<WishHistoryResponseDto> wishHistoryList = wishHistoryService.findWishHistoryListByWishId(wishId);
        model.addAttribute("wishId", wishId);

        // update getMapping을 위해 dto객체를 담음
        model.addAttribute("dto", new WishHistoryUpdateRequestDto());

        // 달성률과 응원 문구를 보여줌
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
    public String createWishHistory(WishHistoryCreateDto wishHistoryCreateDto) {
        wishHistoryService.createWishHistory(wishHistoryCreateDto);
        return "redirect:/wishHistory/" + wishHistoryCreateDto.getWishId();
    }

    @RequestMapping(value = "/wishHistoryInfo/{id}")
    @ResponseBody
    public WishHistoryResponseDto findWishHistoryInfo(@PathVariable("id") Long id) {
        return wishHistoryService.findWishHistoryInfoById(id);
    }

    @PostMapping(value = "/update")
    public String updateWishHistory(WishHistoryUpdateRequestDto wishHistoryUpdateRequestDto) {
        wishHistoryService.updateWishHistory(wishHistoryUpdateRequestDto);
        System.out.println(wishHistoryUpdateRequestDto);
        return "redirect:/wishHistory/" + wishHistoryUpdateRequestDto.getWishId();
    }

    @GetMapping("/delete/{id}")
    public String deleteWishHistory(@PathVariable Long id) {
        wishHistoryService.deleteWishHistory(id);
        WishHistoryResponseDto wishHistoryInfoById = wishHistoryService.findWishHistoryInfoById(id);
        return "redirect:/wishHistory/" + wishHistoryInfoById.getWishId();
    }
}
