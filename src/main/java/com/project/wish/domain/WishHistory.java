package com.project.wish.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class WishHistory {
    private Integer id;
    private Integer wishId;
    private LocalDateTime historyDatetime;
    private Integer amount;
    private LocalDateTime registerDatetime;
    private LocalDateTime modifyDatetime;
}
