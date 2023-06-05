package com.project.wish.controller;

import com.project.wish.domain.Otp;
import com.project.wish.domain.User;
import com.project.wish.dto.UserCreateRequestDto;
import com.project.wish.service.UserService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    /**
     * 회원가입 시에 사용되는 메서드입니다.
     *
     * @param dto user 등록 시 쓰이는 dto 입니다.
     */
    @PostMapping("/user")
    public void createUser(@RequestBody UserCreateRequestDto dto) {
        userService.insertUser(dto);
    }

    @PostMapping("/auth")
    public void auth(@RequestBody User user) {
        userService.auth(user);
    }

    @PostMapping("/otp/check")
    public void check(@RequestBody Otp otp, HttpServletResponse response) {
        if (userService.check(otp)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

}
