package com.project.wish.controller;

import com.project.wish.domain.WishHistory;
import com.project.wish.dto.WishHistoryResponseDto;
import com.project.wish.repository.WishHistoryRepositoryImpl;
import com.project.wish.service.WishHistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/wishHistory")
public class WishHistoryController {

    private final WishHistoryService wishHistoryService;

    @GetMapping("/{wishId}")
    public String list(@PathVariable Integer wishId, Model model) {
        List<WishHistoryResponseDto> wishHistoryResponseDtoList = wishHistoryService.findWishHistoryListByWishId(
            wishId);
        if (wishHistoryResponseDtoList.size() != 0) {
            model.addAttribute("wishHistoryResponseDtoList", wishHistoryResponseDtoList);
        } else {
            model.addAttribute("msg", "아직 위시 기록이 없네요. 새로운 기록을 남겨보세요.");
        }
        return "wishHistory";
    }


//    @GetMapping(value = "/one", produces = "application/json; charset=utf-8")
//    @ResponseBody
//    public WishHistoryResponseDto one(Integer id, Model model){
//        System.out.println("id = " + id);
//        WishHistoryResponseDto wishHistoryOneById = wishHistoryService.findWishHistoryOneById(id);
//        model.addAttribute("wishHistoryOneById", "wishHistoryOneById");
//        return wishHistoryOneById;
//    }

    @RequestMapping(value="/wishHistoryInfo/{id}")
    public String wishHistoryInfo(Integer id, Model model) {
        WishHistoryResponseDto wishHistoryOneById = wishHistoryService.findWishHistoryOneById(id);
        model.addAttribute("wishHistoryOneById", "wishHistoryOneById");
        return null;
    }
}
