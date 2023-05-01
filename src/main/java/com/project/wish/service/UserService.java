package com.project.wish.service;

import com.project.wish.domain.Role;
import com.project.wish.domain.User;
import com.project.wish.dto.UserCreateRequestDto;
import com.project.wish.dto.UserResponseDto;
import com.project.wish.dto.UserResponseDtoByAdmin;
import com.project.wish.dto.UserUpdateRequestDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

public interface UserService {

    void insertUser(UserCreateRequestDto dto);

    UserResponseDto findUserById(Integer id);

    UserResponseDtoByAdmin findUserByIdByAdmin(Integer id);

    List<UserResponseDtoByAdmin> findUsers();

    void updateUser(Integer userId, UserUpdateRequestDto dto);

    void updateUserByAdmin(Integer id);

    void deleteUserById(Integer id);

    boolean isUserIdUnique(String userId);

    boolean isEmailUnique(String email);

    boolean isNicknameUnique(String nickname);

    boolean isPhoneUnique(String phone);

    default User userCreateRequestDtoToUser(UserCreateRequestDto dto) {
        return User.builder()
            .roleId(Role.USER.getRoleId())
            .userId(dto.getUserId())
            .password(dto.getPassword())
            .email(dto.getEmail())
            .name(dto.getName())
            .birth(dto.getBirth())
            .phone(dto.getPhone())
            .nickname(dto.getNickname())
            .isBlock(false)
            .registerDatetime(LocalDateTime.now())
            .modifyDatetime(null)
            .isQuit(false)
            .build();
    }

}
