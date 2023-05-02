package com.project.wish.repository;

import com.project.wish.dto.UserDto;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    SqlSession session;

    public UserDto loginUser(UserDto user) {
        UserDto result = null;
        try {
            String statement = "user.login";
            result = session.selectOne(statement, user);
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public UserDto getUserInfo(UserDto user) {
        UserDto result = null;

        try {
            String statement = "user.getUserInfo";
            result = session.selectOne(statement, user);
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
