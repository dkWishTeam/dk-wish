package com.project.wish.controller;

import com.project.wish.dto.LoginDto;
import com.project.wish.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    private final UserService userService;

    /**
     * 메인 페이지 요청 메서드
     * @return 메인페이지로 리턴해줍니다.
     */
    @ResponseBody
    @GetMapping("/hello")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("hellllo");
    }
    @RequestMapping("/")
    public String mainView() {
        return "index";

    }

    /**
     * 로그인페이지 요청 메서드
     * @return 로그인페이지로 리턴
     */
    @RequestMapping("/login")
    public String loginView(HttpSession session) {
        boolean result = userService.isLogin(session);
        if(result == true)
            return "redirect:/";

        return "login";
    }
//    /**
//     * 로그인페이지 요청 메서드
//     * @return 로그인페이지로 리턴
//     */
//    @RequestMapping("/login")
//    public String loginView(HttpSession session, Model model,
//        @CookieValue(value = "rememberUserId", required = false) Cookie rememberCookie) {
//        boolean result = userService.isLogin(session);
//        if(result == true)
//            return "redirect:/";
//
//        if(rememberCookie != null) {
//            model.addAttribute("rememberCookie", rememberCookie.getValue());
//            model.addAttribute("rememberCheckBox", true);
//        }
//        return "login";
//    }

    /**
     * 로그인 체크 메서드
     * @param model
     * @param user
     * @param session
     *
     * @return 성공하면 메인페이지 실패하면 다시 로그인 페이지
     */
    @RequestMapping("/loginCheck")
    public String login(Model model, LoginDto user, HttpSession session, boolean remember, HttpServletResponse response) {
        log.debug("[Login]login user " + user);
        boolean result = userService.findLoginUser(user, session, model, remember, response);
        if(result == true) {
            log.debug("[Login]Success login");
            return "redirect:/";
        }

        log.debug("[Login]Failed login");
        return "login";
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
}
