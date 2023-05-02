package com.project.wish.repository;

import com.project.wish.domain.User;
import com.project.wish.dto.LoginDto;
import java.util.List;

public interface UserRepository {

    public LoginDto findLoginUser(LoginDto user);
    
    public LoginDto findLoginUserInfo(LoginDto user);
    
    void insertUser(User user);

    User findUserById(Integer id);

    User findUserByIdByAdmin(Integer id);

    List<User> findUsers();

    void updateUser(User user);

    void updateUserBlockByAdmin(Integer id);

    void updateUserUnBlockByAdmin(Integer id);

    void deleteUser(int userId);

    User findUserByPhone(String phone);

    User findUserByUserId(String userId);

    User findUserByEmail(String email);

    User findUserByNickname(String nickname);
}
