package com.project.wish.repository;

import com.project.wish.domain.User;
import com.project.wish.exception.SignUpFailException;
import java.util.List;
import com.project.wish.dto.LoginDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    SqlSession session;
  
    @Override
    public LoginDto findLoginUser(LoginDto user) {
        LoginDto result = null;
        try {
            String statement = "wishdb.login";
            result = session.selectOne(statement, user);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public LoginDto findLoginUserInfo(LoginDto user) {
        LoginDto result = null;

        try {
            String statement = "wishdb.getLoginUserInfo";
            result = session.selectOne(statement, user);
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void insertUser(User user) {
        String statement = null;
        try {
            statement = "wishdb.insertUser";
        } catch (Exception e){
            throw new SignUpFailException();
        }
        session.insert(statement, user);
    }

    @Override
    public User findUserById(Long id) {
        String statement = "wishdb.findUserById";
        return session.selectOne(statement, id);
    }

    @Override
    public User findUserByIdByAdmin(Long id) {
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
    public void updateUserBlockByAdmin(Long id) {
        String statement = "wishdb.updateUserBlockByAdmin";
        session.update(statement, id);
    }

    @Override
    public void updateUserUnBlockByAdmin(Long id) {
        String statement = "wishdb.updateUserUnBlockByAdmin";
        session.update(statement, id);
    }

    @Override
    public void deleteUser(Long id) {
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
