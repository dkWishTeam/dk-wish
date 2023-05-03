package com.project.wish.repository;

import com.project.wish.domain.Wish;

public interface WishRepository {

    //Create
    void insertWish(Wish inputWish);

    //Read
    Wish findWishById(Long id);

    void updateWish(Wish updateWish);

    //Delete
    void deleteWish(Long id);

}
