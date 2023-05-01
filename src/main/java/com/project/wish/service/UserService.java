package com.project.wish.service;

import com.project.wish.dto.UserDto;

public interface UserService {

    void insertUser(UserDto dto);
    void deleteUserById(int id);

}
