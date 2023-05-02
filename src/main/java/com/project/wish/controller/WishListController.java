package com.project.wish.controller;

import com.project.wish.dto.WishListDto;
import com.project.wish.service.WishListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WishListController {
    @Autowired
    WishListService wishListService;

//    @RequestMapping(value = "/", produces = "application/json; charset=utf-8")
//    @ResponseBody
//    public List<WishListDto> getJsonAllWishList() {
//        return wishListService.getAllWishList();
//    }

    @RequestMapping("/list/{path}")
    public String getWishList(@PathVariable("path") String path, Model model) {
        model.addAttribute("list", wishListService.getWishList(path));
        return "index";
    }
}
