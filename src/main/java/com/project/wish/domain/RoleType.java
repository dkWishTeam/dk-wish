package com.project.wish.domain;

public enum RoleType {
    ADMIN(1), USER(2);

    private final int roleId;

    RoleType(int roleId){
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}
