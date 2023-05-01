package com.project.wish.domain;

public enum Role {
    USER(0), ADMIN(1);

    private final int roleId;

    Role(int roleId){
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}
