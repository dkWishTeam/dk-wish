package com.project.wish.controller;

import com.project.wish.dto.WishListDto;
import com.project.wish.service.WishListService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    @RequestMapping("/wishList/{path}")
    public String getWishList(@PathVariable("path") String path, Model model) {
        model.addAttribute("wishList", wishListService.getWishList(path));
        return "index";
    }
}
