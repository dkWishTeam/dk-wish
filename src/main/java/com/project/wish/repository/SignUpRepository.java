package com.project.wish.repository;

import com.project.wish.domain.User;

public interface SignUpRepository {

    int signUp(User user);

    void isEmailUnique(String email);
}
