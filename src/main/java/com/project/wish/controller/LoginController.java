package com.project.wish.controller;

import com.project.wish.service.UserService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    private final UserService userService;

    /**
     * 메인 페이지 요청 메서드
     *
     * @return 메인페이지로 리턴해줍니다.
     */

    @RequestMapping("/")
    public ResponseEntity<Void> mainView() {
        return ResponseEntity.ok(null);

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
//

    /**
     * 세션에 있는 유저 정보들을 지워주는 메서드
     *
     * @param session
     * @return 메인페이지로 리턴해준다.
     */
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        userService.logout(session);
        return ResponseEntity.ok(null);
    }
}
