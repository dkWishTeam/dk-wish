package com.project.wish.repository;

import com.project.wish.dto.WishListDto;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepositoryImpl implements WishListRepository{
    @Autowired
    SqlSession session;

    @Override
    public List<WishListDto> getAllWishList() {
        List<WishListDto> list = null;
        try {
            String statement = "wishList.all";
            list = session.selectList(statement);
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<WishListDto> getCompletionWishList() {
        List<WishListDto> list = null;
        try {
            String statement = "wishList.completion";
            list = session.selectList(statement);
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<WishListDto> getOngoingWishList() {
        List<WishListDto> list = null;
        try {
            String statement = "wishList.ongoing";
            list = session.selectList(statement);
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<WishListDto> getNewWishList() {
        List<WishListDto> list = null;
        try {
            String statement = "wishList.new";
            list = session.selectList(statement);
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public String getNickName(WishListDto wishList) {
        String result = null;
        try {
            String statement = "wishList.getNickName";
            result = session.selectOne(statement, wishList);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
