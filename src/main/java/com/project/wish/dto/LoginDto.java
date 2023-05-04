package com.project.wish.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDto {
    Long id;
    Integer roleId;

    String userId;
    String password;
    String nickname;

    String email;

    Integer isBlock;
    Integer isQuit;
}
