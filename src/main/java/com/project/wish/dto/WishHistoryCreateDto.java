package com.project.wish.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WishHistoryCreateDto {
    private Long wishId;
    private java.sql.Date historyDatetime;
    private Long amount;
}
