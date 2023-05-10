package com.project.wish.repository;

import com.project.wish.domain.WishHistory;
import com.project.wish.dto.LoginDto;
import com.project.wish.dto.WishHistoryRateDto;
import com.project.wish.dto.WishUserDto;
import com.project.wish.exception.NotFoundWishHistoryException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishHistoryRepositoryImpl implements WishHistoryRepository {

    @Autowired
    SqlSession session;

    @Override
    public List<WishHistory> findWishHistoryListByWishId(Long wishId) {
        String statement = "WishHistoryMapper.findWishHistoryListByWishId";
        return session.selectList(statement, wishId);
    }

    @Override
    public WishHistory findWishHistoryInfoById(Long id) {
        String statement = "WishHistoryMapper.findHistoryInfoById";
        return session.selectOne(statement, id);
    }

    @Override
    public WishHistoryRateDto findRateByWishId(Long wishId) throws NotFoundWishHistoryException {
        String statement = "WishHistoryMapper.findRateByWishId";
        WishHistoryRateDto wishHistoryRateDto = session.selectOne(statement, wishId);
        System.out.println("WishHistoryRepositoryImpl.findRateByWishId" + wishHistoryRateDto.toString());
        return wishHistoryRateDto;
    }

    @Override
    public void createWishHistory(WishHistory wishHistory) {
        String statement = "WishHistoryMapper.insertWishHistory";
        session.insert(statement, wishHistory);
        String statement2 = "WishHistoryMapper.updateCompletionStatus";
        session.update(statement2, wishHistory.getWishId());
    }

    @Override
    public void updateWishHistory(WishHistory wishHistory) {
        String statement = "WishHistoryMapper.updateWishHistory";
        session.update(statement, wishHistory);
        String statement2 = "WishHistoryMapper.updateCompletionStatus";
        session.update(statement2, wishHistory.getWishId());
    }

    @Override
    public boolean deleteWishHistory(Long id) {
        String statement = "WishHistoryMapper.deleteWishHistory";
        String statement2 = "WishHistoryMapper.updateCompletionStatusByDelete";
        int delete = session.delete(statement, id);
        session.update(statement2, id);
        return delete == 1;

    }

    @Override
    public WishUserDto findWishUserInfo(Long id) {
        WishUserDto result = null;
        try {
            String statement = "WishHistoryMapper.findWishUser";
            result = session.selectOne(statement, id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
