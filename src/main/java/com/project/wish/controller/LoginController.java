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

    /**
     * 메인 페이지 요청 메서드
     *
     * @param model 메인페이지를 요청하면 위시리스트 전체를 모델 객체에 전달해줍니다.
     * @return 메인페이지로 리턴해줍니다.
     */
    @RequestMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("wishList", wishListService.getWishList("all"));
        return "index";
    }

    /**
     * 로그인페이지 요청 메서드
     * @return 로그인페이지로 리턴
     */
    @RequestMapping("/login")
    public String login(HttpSession session) {
        boolean result = userService.loginMaintain(session);
        if(result == true)
            return "redirect:/";

        return "login";
    }

    /**
     * 로그인 체크 메서드
     * @param model
     * @param user
     * @param session
     *
     * @return 성공하면 메인페이지 실패하면 다시 로그인 페이지
     */
    @RequestMapping("/loginCheck")
    public String loginCheck(Model model, LoginDto user, HttpSession session, boolean remember) {
        System.out.println(remember);
        boolean result = userService.loginCheck(user, session, model);
        if(result == true) {
            return "redirect:/";
        } else {
            return "login";
        }
    }

    /**
     * 세션에 있는 유저 정보들을 지워주는 메서드
     * @param session
     * @return 메인페이지로 리턴해준다.
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        userService.logout(session);
        return "redirect:/";
    }

    /** 메인페이지에 있는 등록버튼 메서드
     *
     * @param session 세션에 아이디가 있나 없나(로그인이 되있나)
     * @return 있으면 wish 등록 페이지 없으면 로그인페이지
     */
    @RequestMapping("/main/add")
    public String mainPageWishAdd(HttpSession session) {
        boolean result = userService.loginMaintain(session);
        if(result == true) {
            return "index";
        }

        return "login";
    }
}
