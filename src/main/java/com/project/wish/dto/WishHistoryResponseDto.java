package com.project.wish.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class WishHistoryResponseDto {

    private Long wishId;
    private Long id;
    private LocalDateTime historyDatetime;
    private LocalDateTime registerDatetime;
    private Long amount;

    @Override
    public String toString() {
        return this.getId() + " " + this.getWishId() + " " +  this.getAmount() +  " " + this.getHistoryDatetime();
    }
}

