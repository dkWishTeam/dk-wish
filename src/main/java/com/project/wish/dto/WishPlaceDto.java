package com.project.wish.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WishPlaceDto {
    Long id;
    Long userId;

    // 이미지
    String image;
    String imageSrc;

    // product name
    String productName;
    // goal amount
    Long goalAmount;

    String goalAmountFormat;
    String ongoingAmountFormat;

    String nickname;
    int percentage;
}
