package com.project.wish.domain;

public enum RoleType {
    ROLE_ADMIN(1), ROLE_USER(2);

    private final int roleId;

    RoleType(int roleId){
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}
