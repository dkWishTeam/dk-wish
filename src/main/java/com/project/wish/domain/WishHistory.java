package com.project.wish.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class WishHistory {
    private Long id;
    private Long wishId;
    private LocalDateTime historyDatetime;
    private Long amount;
    private LocalDateTime registerDatetime;
    private LocalDateTime modifyDatetime;


}
