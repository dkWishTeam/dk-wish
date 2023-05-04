package com.project.wish.repository;

import com.project.wish.dto.WishPlaceDto;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WishPlaceRepositoryImpl implements WishPlaceRepository {
    @Autowired
    SqlSession session;

    @Override
    public List<WishPlaceDto> getAllWishPlaceList() {
        List<WishPlaceDto> list = null;
        try {
            String statement = "wishPlace.all";
            list = session.selectList(statement);
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<WishPlaceDto> getCompletionWishPlaceList() {
        List<WishPlaceDto> list = null;
        try {
            String statement = "wishPlace.completion";
            list = session.selectList(statement);
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<WishPlaceDto> getOngoingWishPlaceList() {
        List<WishPlaceDto> list = null;
        try {
            String statement = "wishPlace.ongoing";
            list = session.selectList(statement);
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<WishPlaceDto> getNewWishPlaceList() {
        List<WishPlaceDto> list = null;
        try {
            String statement = "wishPlace.new";
            list = session.selectList(statement);
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

}
