package com.project.wish.service;

import com.project.wish.domain.Role;
import com.project.wish.domain.User;
import com.project.wish.dto.UserCreateRequestDto;
import com.project.wish.dto.UserResponseDto;
import com.project.wish.dto.UserResponseDtoByAdmin;
import com.project.wish.dto.UserUpdateRequestDto;
import com.project.wish.dto.LoginDto;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    public boolean loginCheck(LoginDto user, HttpSession session);
    
    public void logout(HttpSession session);

    public LoginDto getLoginUserInfo(LoginDto user);

    void insertUser(UserCreateRequestDto dto);

    UserResponseDto findUserById(Integer id);

    UserResponseDtoByAdmin findUserByIdByAdmin(Integer id);

    List<UserResponseDtoByAdmin> findUsers();

    void updateUser(Integer userId, UserUpdateRequestDto dto);

    void updateUserBlockByAdmin(Integer id);

    void updateUserUnBlockByAdmin(Integer id);

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

    default User userUpdateRequestDtoToUser(User user, UserUpdateRequestDto dto) {
        user.setUserId(dto.getUserId());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setBirth(dto.getBirth());
        user.setPhone(dto.getPhone());
        user.setNickname(dto.getNickname());
        user.setModifyDatetime(LocalDateTime.now());
        return user;
    }

    default UserResponseDto userToUserResponseDto(User user) {
        return UserResponseDto.builder()
            .id(user.getId())
            .userId(user.getUserId())
            .email(user.getEmail())
            .name(user.getName())
            .birth(user.getBirth())
            .phone(user.getPhone())
            .nickname(user.getNickname())
            .build();
    }

    default UserResponseDtoByAdmin userToUserResponseDtoByAdmin(User user) {
        return UserResponseDtoByAdmin.builder()
            .id(user.getId())
            .nickname(user.getNickname())
            .isBlock(user.isBlock())
            .build();
    }
}
