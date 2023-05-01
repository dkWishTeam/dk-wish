package com.project.wish.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String user_id;
    private String password;
    private String email;
    private String name;
    private Date birth;
    private String phone;
    private String nickname;
}
