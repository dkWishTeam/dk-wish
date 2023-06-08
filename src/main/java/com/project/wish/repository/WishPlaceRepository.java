package com.project.wish.repository;

import com.project.wish.domain.WishHistory;
import com.project.wish.dto.WishPlaceDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.repository.query.Param;

public interface WishPlaceRepository extends JpaRepository<WishHistory, Long> {

    // ALL
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
            "WHERE w.isPublic = true AND u.isBlock = false " +
            "GROUP BY w.id")
    List<WishPlaceDto> findAllWishPlaceList();

    // Completion
    @Query(
        "SELECT new com.project.wish.dto.WishPlaceDto(w.id as id, w.user.id as userId, u.nickname as nickname, w.image as imageSrc, w.productName as productName, w.goalAmount as goalAmountFormat, "
            + "SUM(wh.amount) as ongoingAmountFormat, floor(((SUM(wh.amount)) / w.goalAmount) * 100) as percentage) " +
            "FROM WishHistory wh " +
            "INNER JOIN wh.wish w ON w.id = wh.wish.id " +
            "left JOIN w.user u ON u.id = w.user.id " +
            "WHERE w.isPublic = true AND u.isBlock = false " +
            "GROUP BY w.id " +
            "HAVING SUM(wh.amount) >= w.goalAmount")
    List<WishPlaceDto> findCompletionWishPlaceList(Pageable pageable);

    @Query(
        "SELECT new com.project.wish.dto.WishPlaceDto(w.id as id, w.user.id as userId, u.nickname as nickname, w.image as imageSrc, w.productName as productName, w.goalAmount as goalAmountFormat, "
            + "SUM(wh.amount) as ongoingAmountFormat, floor(((SUM(wh.amount)) / w.goalAmount) * 100) as percentage) " +
            "FROM WishHistory wh " +
            "INNER JOIN wh.wish w ON w.id = wh.wish.id " +
            "left JOIN w.user u ON u.id = w.user.id " +
            "WHERE w.isPublic = true AND u.isBlock = false " +
            "GROUP BY w.id " +
            "HAVING SUM(wh.amount) >= w.goalAmount")
    List<WishPlaceDto> findCompletionWishPlaceList();

    // ONGOING
    @Query(
        "SELECT new com.project.wish.dto.WishPlaceDto(w.id as id, w.user.id as userId, u.nickname as nickname, w.image as imageSrc, w.productName as productName, w.goalAmount as goalAmountFormat, "
            + "SUM(wh.amount) as ongoingAmountFormat, floor(((SUM(wh.amount)) / w.goalAmount) * 100) as percentage) " +
            "FROM WishHistory wh " +
            "INNER JOIN wh.wish w ON w.id = wh.wish.id " +
            "left JOIN w.user u ON u.id = w.user.id " +
            "WHERE w.isPublic = true AND u.isBlock = false " +
            "GROUP BY w.id " +
            "HAVING SUM(wh.amount) < w.goalAmount")
    List<WishPlaceDto> findOngoingWishPlaceList(Pageable pageable);

    @Query(
        "SELECT new com.project.wish.dto.WishPlaceDto(w.id as id, w.user.id as userId, u.nickname as nickname, w.image as imageSrc, w.productName as productName, w.goalAmount as goalAmountFormat, "
            + "SUM(wh.amount) as ongoingAmountFormat, floor(((SUM(wh.amount)) / w.goalAmount) * 100) as percentage) " +
            "FROM WishHistory wh " +
            "INNER JOIN wh.wish w ON w.id = wh.wish.id " +
            "left JOIN w.user u ON u.id = w.user.id " +
            "WHERE w.isPublic = true AND u.isBlock = false " +
            "GROUP BY w.id " +
            "HAVING SUM(wh.amount) < w.goalAmount")
    List<WishPlaceDto> findOngoingWishPlaceList();

    // NEW
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

    @Query(
        "SELECT new com.project.wish.dto.WishPlaceDto(w.id as id, w.user.id as userId, u.nickname as nickname, w.image as imageSrc, w.productName as productName, w.goalAmount as goalAmountFormat, "
            + "SUM(wh.amount) as ongoingAmountFormat, floor(((SUM(wh.amount)) / w.goalAmount) * 100) as percentage) " +
            "FROM WishHistory wh " +
            "INNER JOIN wh.wish w ON w.id = wh.wish.id " +
            "left JOIN w.user u ON u.id = w.user.id " +
            "WHERE w.isPublic = true AND u.isBlock = false " +
            "GROUP BY w.id " +
            "order by w.registerDatetime desc")
    List<WishPlaceDto> findNewWishPlaceList();

    @Query(
        "SELECT new com.project.wish.dto.WishPlaceDto(w.id as id, w.user.id as userId, u.nickname as nickname, w.image as imageSrc, w.productName as productName, w.goalAmount as goalAmountFormat, "
            + "SUM(wh.amount) as ongoingAmountFormat, floor(((SUM(wh.amount)) / w.goalAmount) * 100) as percentage) " +
            "FROM WishHistory wh " +
            "INNER JOIN wh.wish w ON w.id = wh.wish.id " +
            "left JOIN w.user u ON u.id = w.user.id " +
            "WHERE w.isPublic = true AND u.isBlock = false AND w.productName LIKE %:search% " +
            "GROUP BY w.id")
    List<WishPlaceDto> findSearchWishPlaceList(@Param("search") String search, Pageable pageable);

    @Query(
        "SELECT new com.project.wish.dto.WishPlaceDto(w.id as id, w.user.id as userId, u.nickname as nickname, w.image as imageSrc, w.productName as productName, w.goalAmount as goalAmountFormat, "
            + "SUM(wh.amount) as ongoingAmountFormat, floor(((SUM(wh.amount)) / w.goalAmount) * 100) as percentage) " +
            "FROM WishHistory wh " +
            "INNER JOIN wh.wish w ON w.id = wh.wish.id " +
            "left JOIN w.user u ON u.id = w.user.id " +
            "WHERE w.isPublic = true AND u.isBlock = false AND w.productName LIKE %:search% " +
            "GROUP BY w.id")
    List<WishPlaceDto> findSearchWishPlaceList(@Param("search") String search);
}
