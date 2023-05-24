package com.project.wish.repository;

import com.project.wish.domain.Role;
import com.project.wish.domain.RoleType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleType(RoleType user);
}
