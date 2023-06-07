package com.project.wish.repository;

import com.project.wish.domain.Wish;
import com.project.wish.domain.WishHistory;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WishHistoryRepository extends JpaRepository<WishHistory, Long> {
    //    1. List<WishHistory> findWishHistoryListByWishId(Long wishId);

//    2. R : WishHistory findWishHistoryInfoById(Long id);

//    3. WishHistoryRateDto findRateByWishId(Long wishId);



//    <select id="findRateByWishId" parameterType="java.lang.Long" resultType="WishHistoryRateDto">
//    SELECT w.id                                            as wishId,
//    FLOOR(((SUM(wh.amount) / w.goal_amount) * 100)) as percent
//--     SELECT w.id as wishId, ((SUM(wh.amount) / w.goal_amount)*100)) as percent
//    FROM wish w
//    LEFT JOIN wish_history wh ON w.id = wh.wish_id
//    WHERE w.id = #{id}
//  </select>



//    @Query("SELECT w.id, FLOOR(((SUM(wh.amount) / w.goalAmount) * 100)) as percent " +
//        "FROM WishHistory wh JOIN fetch wh.wish w " +
//        "WHERE w.id = :id")
//    WishHistoryRateDto findRateByWishId(@Param("id") Long wishId);


//    4. C : void createWishHistory(WishHistory wishHistory);

//    5. U : void updateWishHistory(WishHistory wishHistory); : Service에서 설정 필요

//    6. D : boolean deleteWishHistory(Long id);

    @Query("SELECT wh "
        + "FROM WishHistory wh "
        + "JOIN wh.wish w "
        + "WHERE w.id = :wishId "
        + "ORDER BY wh.historyDatetime")
    Page<WishHistory> findAllByWish(@Param("wishId") Long wishId, Pageable pageable);
//    List<WishHistory> findAllByWish_Id;

}