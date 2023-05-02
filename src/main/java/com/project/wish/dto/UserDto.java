package com.project.wish.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    Long id;
    Integer role_id;

    String user_id;
    String password;
    String email;
    String name;

    Date birth;
    String phone;
    String nickname;

    Integer is_block;
    LocalDateTime register_datetime;
    LocalDateTime modify_datetime;
    Integer is_quit;
}
