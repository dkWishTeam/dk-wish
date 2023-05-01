package com.project.wish.controller;

import com.project.wish.dto.UserDto;
import com.project.wish.exception.SignUpFailException;
import com.project.wish.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public void createUser(@RequestBody UserDto dto) {
        userService.insertUser(dto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id
        , @RequestBody UserDto) {
        userService.updateUser(id);
    }

    @GetMapping
    public String getSignUpForm() {
        return "sign-up";
        //todo 해당 경로의 Path hierarchy 구조 변경
    }

    @PostMapping
    public String signUp(@RequestBody UserDto signUpDto) {
        boolean isSignUpComplete = signUpService.signUp(signUpDto);
        //todo 회원가입 시점에서 닉네임과 이메일과 전화번호가 unique 한지 check
        if (isSignUpComplete) {
            return "main";
            //todo main 페이지 주소로 변경
        } else {
            throw new SignUpFailException("회원가입에 실패했습니다.");
            // todo 회원가입 실패 페이지
        }
    }

    @PostMapping("/email-duplicate-check")
    public boolean isEmailUnique(String email) {
        boolean isEmailDuplicate = signUpService.isEmailUnique(email);
        return !isEmailDuplicate;
    }

    @PostMapping("/nickname-duplicate-check")
    public boolean isNicknameUnique(String nickname) {
        boolean isNicknameDuplicate = signUpService.isNicknameUnique(nickname);
        return !isNicknameDuplicate;
    }

    @PostMapping("/phone-duplicate-check")
    public boolean isPhoneUnique(String phone) {
        boolean isPhoneDuplicate = signUpService.isPhoneUnique(phone);
        return !isPhoneDuplicate;
    }

}
