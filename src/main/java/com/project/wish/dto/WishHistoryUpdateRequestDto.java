package com.project.wish.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@Builder
@ToString
public class WishHistoryUpdateRequestDto {

    private Long wishId;
    private Long id;
    private java.sql.Date historyDatetime;
    private Long amount;

    public WishHistoryUpdateRequestDto() {
    }

    public WishHistoryUpdateRequestDto(Long wishId, Long id, Date historyDatetime, Long amount) {
        this.wishId = wishId;
        this.id = id;
        this.historyDatetime = historyDatetime;
        this.amount = amount;
    }
}
