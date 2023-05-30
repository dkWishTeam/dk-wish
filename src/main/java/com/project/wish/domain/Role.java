package com.project.wish.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
public class Role {

    @Id
    private Integer id;

    @Column(name = "role_type")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

}
