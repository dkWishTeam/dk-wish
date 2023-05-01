package com.project.wish.service;

import com.project.wish.dto.UserDto;
import javax.servlet.http.HttpSession;

public interface UserService {
    public boolean loginCheck(UserDto user, HttpSession session);
    public void logout(HttpSession session);

    public UserDto getUserInfo(UserDto user);
}
