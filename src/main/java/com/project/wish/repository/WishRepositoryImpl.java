package com.project.wish.repository;

import com.project.wish.domain.Wish;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepositoryImpl implements WishRepository {

    @Autowired
    private SqlSession session;

    @Override
    public void insertWish(Wish inputWish) {
        String sql = "wish.insertWish";
        session.insert(sql, inputWish);
    }

    @Override
    public Wish findWishById(Long id) {
        System.out.println("WishRepositoryImpl.findWishById : " + id);
        String sql = "wish.findWishById";
        return session.selectOne(sql, id);
    }

    @Override
    public Wish findWishByWishId(Long wishId) {
        return null;
    }

    @Override
    public List<Wish> findWishListByUserID(Long id) {
        return null;
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
