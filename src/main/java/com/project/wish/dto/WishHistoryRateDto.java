package com.project.wish.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
@RequiredArgsConstructor
public class WishHistoryRateDto {
    private Long wishId;
    private Long percent;
    private String cheerUpPhrase;

    @Builder
    public WishHistoryRateDto(Long wishId, Long percent, String cheerUpPhrase) {
        this.wishId = wishId;
        this.percent = percent;
        this.cheerUpPhrase = cheerUpPhrase;
    }
}
