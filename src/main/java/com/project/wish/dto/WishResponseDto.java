package com.project.wish.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class WishResponseDto {

    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String image;
    private String productName;
    private long goalAmount;
    private Date goalDate;
    private boolean isPublic;
    private boolean completionStatus;
    private LocalDateTime registerDatetime;
    private LocalDateTime modifyDatetime;

}
