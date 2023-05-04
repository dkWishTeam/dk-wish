package com.project.wish.repository;

import com.project.wish.domain.User;
import com.project.wish.dto.LoginDto;
import java.util.List;

public interface UserRepository {

    public LoginDto findLoginUser(LoginDto user);
    
    public LoginDto findLoginUserInfo(LoginDto user);
    
    void insertUser(User user);

    User findUserById(Long id);

    User findUserByIdByAdmin(Long id);

    List<User> findUsers();

    void updateUser(User user);

    void updateUserBlockByAdmin(Long id);

    void updateUserUnBlockByAdmin(Long id);

    void deleteUser(Long userId);

    User findUserByPhone(String phone);

    User findUserByUserId(String userId);

    User findUserByEmail(String email);

    User findUserByNickname(String nickname);
}
