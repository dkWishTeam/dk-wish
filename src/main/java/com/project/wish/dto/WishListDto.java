package com.project.wish.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WishListDto {
    Long id;
    Long user_id;

    // 이미지
    byte[] image;
    // product name
    String product_name;
    // goal amount
    Long goal_amount;

    String nickname;
    int percentage_completed;
}
