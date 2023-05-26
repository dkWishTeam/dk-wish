package com.project.wish.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class WishUserDto {
    Long userId;
    String nickname;
}
