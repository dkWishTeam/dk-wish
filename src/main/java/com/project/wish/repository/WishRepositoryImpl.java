package com.project.wish.repository;

import com.project.wish.domain.Wish;
import java.util.List;

import com.project.wish.exception.WishCreateException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepositoryImpl implements WishRepository {

    @Autowired
    private SqlSession session;

    @Override
    public Long insertWish(Wish inputWish) {
        String sql = "wish.insertWish";
        try {
            session.insert(sql, inputWish);
        } catch (WishCreateException e) {
            System.out.println(e.getMessage());
        }
        return inputWish.getId();
    }

    @Override
    public Wish findWishById(Long id) {
        String sql = "wish.findWishById";
        return session.selectOne(sql, id);
    }

    @Override
    public Wish findWishByWishId(Long wishId) {
        return null;
    }

    @Override
    public List<Wish> findWishListByUserID(Long id) {
        String sql = "wish.userWishList";
        return session.selectList(sql, id);
    }

    @Override
    public void updateWish(Wish updateWish) {
        String sql = "wish.updateWish";
        session.update(sql, updateWish);
    }

    @Override
    public void deleteWish(Long id) {
        String sql = "wish.deleteWish";
        session.delete(sql, id);
    }



}
