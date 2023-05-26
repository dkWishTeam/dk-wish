package com.project.wish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WishPlaceDto {
    Long id;
    Long userId;

    String nickname;

    // 이미지
    String imageSrc;

    // product name
    String productName;

    Long goalAmountFormat;
    Long ongoingAmountFormat;

    int percentage;
}
