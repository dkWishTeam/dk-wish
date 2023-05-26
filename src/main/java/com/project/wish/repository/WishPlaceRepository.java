package com.project.wish.repository;

import com.project.wish.domain.WishHistory;
import com.project.wish.dto.WishPlaceDto;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WishPlaceRepository extends JpaRepository<WishHistory, Long> {

    @Query(
        "SELECT new com.project.wish.dto.WishPlaceDto(w.id as id, w.user.id as userId, u.nickname as nickname, w.image as imageSrc, w.productName as productName, w.goalAmount as goalAmountFormat, "
            + "SUM(wh.amount) as ongoingAmountFormat, floor(((SUM(wh.amount)) / w.goalAmount) * 100) as percentage) " +
            "FROM WishHistory wh " +
            "INNER JOIN wh.wish w ON w.id = wh.wish.id " +
            "left JOIN w.user u ON u.id = w.user.id " +
            "WHERE w.isPublic = true AND u.isBlock = false " +
            "GROUP BY w.id")
    List<WishPlaceDto> findAllWishPlaceList(Pageable pageable);

    @Query(
        "SELECT new com.project.wish.dto.WishPlaceDto(w.id as id, w.user.id as userId, u.nickname as nickname, w.image as imageSrc, w.productName as productName, w.goalAmount as goalAmountFormat, "
            + "SUM(wh.amount) as ongoingAmountFormat, floor(((SUM(wh.amount)) / w.goalAmount) * 100) as percentage) " +
            "FROM WishHistory wh " +
            "INNER JOIN wh.wish w ON w.id = wh.wish.id " +
            "left JOIN w.user u ON u.id = w.user.id " +
            "WHERE w.isPublic = true AND u.isBlock = false and w.completionStatus = true " +
            "GROUP BY w.id")
    List<WishPlaceDto> findCompletionWishPlaceList(Pageable pageable);


    @Query(
        "SELECT new com.project.wish.dto.WishPlaceDto(w.id as id, w.user.id as userId, u.nickname as nickname, w.image as imageSrc, w.productName as productName, w.goalAmount as goalAmountFormat, "
            + "SUM(wh.amount) as ongoingAmountFormat, floor(((SUM(wh.amount)) / w.goalAmount) * 100) as percentage) " +
            "FROM WishHistory wh " +
            "INNER JOIN wh.wish w ON w.id = wh.wish.id " +
            "left JOIN w.user u ON u.id = w.user.id " +
            "WHERE w.isPublic = true AND u.isBlock = false and w.completionStatus = false " +
            "GROUP BY w.id")
    List<WishPlaceDto> findOngoingWishPlaceList(Pageable pageable);

    @Query(
        "SELECT new com.project.wish.dto.WishPlaceDto(w.id as id, w.user.id as userId, u.nickname as nickname, w.image as imageSrc, w.productName as productName, w.goalAmount as goalAmountFormat, "
            + "SUM(wh.amount) as ongoingAmountFormat, floor(((SUM(wh.amount)) / w.goalAmount) * 100) as percentage) " +
            "FROM WishHistory wh " +
            "INNER JOIN wh.wish w ON w.id = wh.wish.id " +
            "left JOIN w.user u ON u.id = w.user.id " +
            "WHERE w.isPublic = true AND u.isBlock = false " +
            "GROUP BY w.id " +
            "order by w.registerDatetime desc")
    List<WishPlaceDto> findNewWishPlaceList(Pageable pageable);
}
