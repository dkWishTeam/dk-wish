package com.project.wish.repository;

import com.project.wish.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    SqlSession session;

    @Override
    public boolean insertUser(User user) {
        String statement = "lab1.exam1";
        if(session.insert(statement,user) == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        String statement = "lab1.exam1";
        if(session.insert(statement,user) == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean findUser(int userId) {
        return false;
    }

    @Override
    public boolean findUsers() {
        return false;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByNickname(String nickname) {
        return null;
    }

    @Override
    public User findByPhone(String phone) {
        return null;
    }
}
