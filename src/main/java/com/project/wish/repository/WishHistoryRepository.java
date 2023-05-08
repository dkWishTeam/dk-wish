package com.project.wish.repository;

import com.project.wish.domain.WishHistory;
import com.project.wish.dto.WishHistoryRateDto;
import java.util.List;

public interface WishHistoryRepository {
    List<WishHistory> findWishHistoryListByWishId(Long wishId);

    WishHistory findWishHistoryInfoById(Long id);

    WishHistoryRateDto findRateByWishId(Long wishId);

    void createWishHistory(WishHistory wishHistory);

    void updateWishHistory(WishHistory wishHistory);

    boolean deleteWishHistory(Long id);
}
