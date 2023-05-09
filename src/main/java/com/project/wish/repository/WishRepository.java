package com.project.wish.repository;

import com.project.wish.domain.Wish;

import java.util.List;

public interface WishRepository {

    //Create
    Long insertWish(Wish inputWish);

    Wish findWishByWishId(Long wishId);

    //Read
    Wish findWishById(Long id);

    void updateWish(Wish updateWish);

    //Delete
    void deleteWish(Long id);

    List<Wish> findWishListByUserID(Long id);


}
