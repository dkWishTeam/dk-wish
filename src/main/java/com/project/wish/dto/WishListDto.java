package com.project.wish.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WishListDto {
    Long id;
    Long userId;

    // 이미지
    byte[] image;
    // product name
    String productName;
    // goal amount
    Long goalAmount;

    String nickname;
    int percentageCompleted;
}
