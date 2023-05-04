package com.project.wish.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDto {
    Long id;

    String userId;
    String password;
    String nickname;

    Integer isBlock;
    Integer isQuit;
}
