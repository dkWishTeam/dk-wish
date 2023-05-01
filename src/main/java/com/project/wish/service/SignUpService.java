package com.project.wish.service;

import com.project.wish.dto.UserDto;

public interface SignUpService {

    boolean signUp(UserDto signUpDto);

    boolean isEmailUnique(String email);

    boolean isNicknameUnique(String nickname);

    boolean isPhoneUnique(String phone);
}
