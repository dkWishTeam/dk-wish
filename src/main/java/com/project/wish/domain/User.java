package com.project.wish.domain;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "role_id")
    private Role roleId;

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


    @Builder
    public User(Role roleId, String userId, String password, String email, String name, Date birth, String phone,
        String nickname, boolean isBlock, LocalDateTime registerDatetime, LocalDateTime modifyDatetime,
        boolean isQuit) {
        this.roleId = roleId;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.nickname = nickname;
        this.isBlock = isBlock;
        this.registerDatetime = registerDatetime;
        this.modifyDatetime = modifyDatetime;
        this.isQuit = isQuit;
    }
}
