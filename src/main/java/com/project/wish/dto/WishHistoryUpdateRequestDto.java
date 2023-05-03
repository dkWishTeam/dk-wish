package com.project.wish.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WishHistoryUpdateRequestDto {

    private Integer id;
    private LocalDateTime historyDatetime;
    private Integer amount;
}
