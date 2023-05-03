package com.project.wish.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishHistoryCreateDto {

    private Integer id;
    private Integer wishId;
    private LocalDateTime historyDatetime;
    private Integer amount;
    private LocalDateTime registerDateTime;
}
