package com.project.wish.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WishHistoryUpdateRequestDto {

    private Long wishId;
    private Long id;
    private java.sql.Date historyDatetime;
    private Long amount;
}
