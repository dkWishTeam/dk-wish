package com.project.wish.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @JsonProperty(value = "username")
    private String userId;
    private String password;
    private String email;
    private String name;
    private Date birth;
    private String phone;
    private String nickname;
    private boolean isBlock = false;
    private boolean isQuit;

    @Builder
    public User(Role role, String userId, String password, String email, String name, Date birth, String phone,
        String nickname, boolean isBlock, boolean isQuit) {
        this.role = role;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.nickname = nickname;
        this.isBlock = isBlock;
        this.isQuit = isQuit;
    }
}
