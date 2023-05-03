package com.project.wish.repository;

import com.project.wish.domain.WishHistory;
import java.util.List;

public interface WishHistoryRepository {
    List<WishHistory> findWishHistoryListByWishId(Integer wishId);

    WishHistory findWishHistoryOneById(Integer id);

    void insertWishHistory(WishHistory wishHistory);

    void updateWishHistory(WishHistory wishHistory);

    void deleteWishHistory(Integer id);
}
