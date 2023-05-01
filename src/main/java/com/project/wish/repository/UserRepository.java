package com.project.wish.repository;

import com.project.wish.domain.User;

public interface UserRepository {

    boolean insertUser(User user);

    boolean deleteUser(int userId);

    boolean updateUser(User user);

    boolean findUser(int userId);

    boolean findUsers();

    User findByEmail(String email);

    User findByNickname(String nickname);

    User findByPhone(String phone);
}
