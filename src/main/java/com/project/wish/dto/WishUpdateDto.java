package com.project.wish.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.sql.Date;

@Getter
@Setter
@Builder
@ToString
public class WishUpdateDto {

    private String title;
    private String content;
    private String image;
    private String productName;
    private long goalAmount;
    private Date goalDate;
    private boolean isPublic;
    private boolean completionStatus;
    private LocalDateTime modifyDatetime;

}
