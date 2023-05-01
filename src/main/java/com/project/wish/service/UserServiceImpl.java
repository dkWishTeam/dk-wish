package com.project.wish.service;

import com.project.wish.dto.UserDto;
import com.project.wish.repository.UserRepositoryImpl;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepositoryImpl repo;

    @Override
    public boolean loginCheck (UserDto user, HttpSession session) {
        UserDto loginUser = repo.loginUser(user);
        if(loginUser == null) {
            return false;
        }
        if(!loginUser.getPassword().equals(user.getPassword())) {
            return false;
        }
        session.setAttribute("userId", user.getUser_id());
        return true;
    }

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute("userId");
    }

    @Override
    public UserDto getUserInfo (UserDto user) {
        List<UserDto> list = repo.getUserInfo(user);
        return list.get(0);
    }

}
