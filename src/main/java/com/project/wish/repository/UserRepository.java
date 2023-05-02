package com.project.wish.repository;

import com.project.wish.dto.UserDto;
import java.util.List;

public interface UserRepository {
    public UserDto loginUser(UserDto user);
    public UserDto getUserInfo(UserDto user);
}
