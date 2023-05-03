package com.project.wish.controller;

import com.project.wish.dto.LoginDto;
import com.project.wish.service.UserService;
import com.project.wish.service.WishListService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final WishListService wishListService;

    @RequestMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("wishList", wishListService.getWishList("all"));
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginCheck")
    public String loginCheck(Model model, LoginDto user, HttpSession session) {
        boolean result = userService.loginCheck(user, session, model);
        if(result == true) {
            //model.addAttribute("user", userService.getUserInfo(user));
            return "redirect:/";
        } else {
            //model.addAttribute("msg", "아이디 혹은 비밀번호가 다릅니다.");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        userService.logout(session);
        return "redirect:/";
    }
}
