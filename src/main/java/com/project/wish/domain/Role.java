package com.project.wish.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "role")
@Getter
public class Role {

    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
