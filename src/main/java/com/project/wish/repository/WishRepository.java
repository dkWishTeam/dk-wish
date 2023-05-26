package com.project.wish.repository;

import com.project.wish.domain.User;
import com.project.wish.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface WishRepository extends JpaRepository<Wish, Long> {


    @Query("select w from Wish w join w.user where w.user = :user")
    List<Wish> findWishListByUserID(@Param("user") User user);

}
