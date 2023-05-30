package com.project.wish.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UserUpdateRequestDto {

    private Long id;
    private String userId;
    private String password;
    private String email;
    private String name;
    private java.sql.Date birth;
    private String phone;
    private String nickname;
}
