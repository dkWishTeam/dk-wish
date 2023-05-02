package com.project.wish.repository;

import com.project.wish.domain.User;
import java.util.List;
import com.project.wish.dto.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    SqlSession session;
  
    @Override
    public UserDto loginUser(UserDto user) {
        UserDto result = null;
        try {
            String statement = "wishdb.login";
            result = session.selectOne(statement, user);
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
    @Override
    public UserDto getUserInfo(UserDto user) {
        UserDto result = null;

        try {
            String statement = "wishdb.getUserInfo";
            result = session.selectOne(statement, user);
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
        }
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
        return session.selectList(statement);
        // todo xml 파일에서 페이징
    }

    @Override
    public void updateUser(User user) {
        String statement = "wishdb.updateUser";
        session.update(statement, user);
    }

    @Override
    public void updateUserBlockByAdmin(Integer id) {
        String statement = "wishdb.updateUserBlockByAdmin";
        session.update(statement, id);
    }

    @Override
    public void updateUserUnBlockByAdmin(Integer id) {
        String statement = "wishdb.updateUserUnBlockByAdmin";
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
