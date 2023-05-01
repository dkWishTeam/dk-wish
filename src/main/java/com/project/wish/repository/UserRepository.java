package com.project.wish.repository;

import com.project.wish.domain.User;
import com.project.wish.dto.UserResponseDto;
import com.project.wish.dto.UserResponseDtoByAdmin;
import com.project.wish.dto.UserUpdateRequestDto;
import java.util.List;

public interface UserRepository {

    void insertUser(User user);

    User findUserById(Integer id);

    User findUserByIdByAdmin(Integer id);

    List<User> findUsers();

    void updateUser(User user);

    void updateUserByAdmin(Integer id);

    void deleteUser(int userId);

    User findUserByPhone(String phone);

    User findUserByUserId(String userId);

    User findUserByEmail(String email);

    User findUserByNickname(String nickname);
}
