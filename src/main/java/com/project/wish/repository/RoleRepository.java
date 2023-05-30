package com.project.wish.repository;

import com.project.wish.domain.Role;
import com.project.wish.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleType(RoleType user);
}
