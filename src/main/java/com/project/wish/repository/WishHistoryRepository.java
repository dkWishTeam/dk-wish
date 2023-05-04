package com.project.wish.repository;

import com.project.wish.domain.WishHistory;
import java.util.List;

public interface WishHistoryRepository {
    List<WishHistory> findWishHistoryListByWishId(Long wishId);

    WishHistory findWishHistoryInfoById(Long id);

    void createWishHistory(WishHistory wishHistory);

    void updateWishHistory(WishHistory wishHistory);

    void deleteWishHistory(Long id);
}
