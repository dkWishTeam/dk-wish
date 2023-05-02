package com.project.wish.controller;

import com.project.wish.dto.UserDto;
import com.project.wish.service.UserService;
import com.project.wish.service.WishListService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    WishListService wishListService;

    @RequestMapping("/")
    public String main(Model model) {
        model.addAttribute("wishList", wishListService.getWishList("all"));
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginCheck")
    public String loginCheck(Model model, UserDto user, HttpSession session) {
        boolean result = userService.loginCheck(user, session);
        if(result == true) {
            //model.addAttribute("user", userService.getUserInfo(user));
            return "redirect:/";
        } else {
            model.addAttribute("msg", "아이디 혹은 비밀번호가 다릅니다.");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        userService.logout(session);
        return "redirect:/";
    }
}
