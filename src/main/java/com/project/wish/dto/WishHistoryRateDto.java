package com.project.wish.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class WishHistoryRateDto {
    private Long wishId;
    private Long percent;
    private String cheerUpPhrase;
}
