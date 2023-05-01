package com.project.wish.repository;

import com.project.wish.domain.User;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    SqlSession session;

    @Override
    public void insertUser(User user) {
        String statement = "wishdb.insertUser";
        session.insert(statement, user);
    }

    @Override
    public User findUserById(Integer id) {
        String statement = "wishdb.findUserById";
        return session.selectOne(statement, id);
    }

    @Override
    public User findUserByIdByAdmin(Integer id) {
        String statement = "wishdb.findUserByIdByAdmin";
        return session.selectOne(statement, id);
    }

    @Override
    public List<User> findUsers() {
        String statement = "wishdb.findUsers";
        return session.selectOne(statement);
    }

    @Override
    public void updateUser(User user) {
        String statement = "wishdb.updateUser";
        session.update(statement, user);
    }

    @Override
    public void updateUserByAdmin(Integer id) {
        String statement = "wishdb.updateUserByAdmin";
        session.update(statement, id);
    }

    @Override
    public void deleteUser(int id) {
        String statement = "wishdb.deleteUser";
        session.delete(statement, id);
    }

    @Override
    public User findUserByPhone(String phone) {
        String statement = "wishdb.findUserByPhone";
        return session.selectOne(statement, phone);
    }

    @Override
    public User findUserByUserId(String userId) {
        String statement = "wishdb.findUserByUserId";
        return session.selectOne(statement, userId);
    }

    @Override
    public User findUserByEmail(String email) {
        String statement = "wishdb.findUserByEmail";
        return session.selectOne(statement, email);
    }

    @Override
    public User findUserByNickname(String nickname) {
        String statement = "wishdb.findUserByNickname";
        return session.selectOne(statement, nickname);
    }
}
