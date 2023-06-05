package com.project.wish.controller;

import com.project.wish.dto.LoginDto;
import com.project.wish.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    private final UserService userService;
//
//    @GetMapping("/loginView")
//    public String loginView(HttpSession session, Model model,
//        @CookieValue(value = "rememberUserId", required = false) Cookie rememberCookie) {
//        boolean result = userService.isLogined(session);
//        if(result)
//            return "redirect:/";
//
//        if(rememberCookie != null) {
//            model.addAttribute("rememberCookie", rememberCookie.getValue());
//            model.addAttribute("rememberCheckBox", true);
//        }
//        return "login";
//    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto user, HttpSession session) {

        if (userService.isLogined(session)) {
            return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/home").build();
        }
        log.debug("[Login]login user " + user);
        String result = userService.isLogin(user, session);

        if (!result.equals("loginSuccess")) {
            log.debug("[Login]Failed login");
        } else {
            log.debug("[Login]Success login");
            result += ":" + user.getUserId() + ":" + session.getAttribute("nickname") + ":" + session.getAttribute("role");
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        userService.logout(session);
        log.debug("[logout] success ");
        return ResponseEntity.ok("userLogout");
    }
}
