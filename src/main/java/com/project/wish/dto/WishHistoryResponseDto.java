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
@ToString
public class WishHistoryResponseDto {
    private int id;
    private int wishId;
    private LocalDateTime historyDatetime;
    private int amount;
}
