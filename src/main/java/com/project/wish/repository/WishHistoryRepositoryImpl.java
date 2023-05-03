package com.project.wish.repository;

import com.project.wish.domain.WishHistory;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WishHistoryRepositoryImpl implements WishHistoryRepository {

    @Autowired
    SqlSession session;

    @Override
    public List<WishHistory> findWishHistoryListByWishId(Integer wishId) {
        String statement = "WishHistoryMapper.findWishHistoryListByWishId";
        return session.selectList(statement, wishId);
    }

    @Override
    public WishHistory findWishHistoryOneById(Integer id) {
        String statement = "WishHistoryMapper.findWishHistoryOneById";
        return session.selectOne(statement, id);
    }

    @Override
    public void insertWishHistory(WishHistory wishHistory) {
        String statement = "WishHistoryMapper.insertWishHistory";
        session.insert(statement, wishHistory);
    }

    @Override
    public void updateWishHistory(WishHistory wishHistory) {
        String statement = "WishHistoryMapper.updateWishHistory";
        session.update(statement, wishHistory);
    }

    @Override
    public void deleteWishHistory(Integer id) {
        String statement = "WishHistoryMapper.deleteWishHistory";
        session.update(statement, id);
    }

}
