package com.project.wish.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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
