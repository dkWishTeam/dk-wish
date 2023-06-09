package com.project.wish.dto;

import lombok.*;

import java.sql.Date;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WishRequestDto {

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

}
