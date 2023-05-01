package com.project.wish.domain;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class User {

    private int roleId;
    private String userId;
    private String password;
    private String email;
    private String name;
    private Date birth;
    private String phone;
    private String nickname;
    private boolean isBlock;
    private LocalDateTime registerDatetime;
    private LocalDateTime modifyDatetime;
    private boolean isQuit;

}
