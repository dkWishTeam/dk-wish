package com.project.wish.controller;

import com.project.wish.dto.UserDto;
import com.project.wish.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping("main")
    public String main() {
        return "index";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("loginCheck")
    public ModelAndView loginCheck(UserDto user, HttpSession session) {
        boolean result = userService.loginCheck(user, session);
        ModelAndView mav = new ModelAndView();

        if(result == true) {
            mav.addObject("user", userService.getUserInfo(user));
            mav.setViewName("index");
        } else {
            mav.addObject("msg", "아이디 혹은 비밀번호가 다릅니다.");
            mav.setViewName("login");
        }
        return mav;
    }

    @RequestMapping("logout")
    public ModelAndView logout(HttpSession session) {
        userService.logout(session);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
}
