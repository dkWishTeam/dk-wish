package com.project.wish.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequestDto {

    private String userId;
    private String password;
    private String email;
    private String name;
    private java.sql.Date birth;
    private String phone;
    private String nickname;
}
