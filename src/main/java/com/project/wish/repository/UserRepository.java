package com.project.wish.repository;

import com.project.wish.domain.User;
import com.project.wish.dto.LoginDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserId(String userId);

    List<User> findAll();
    
    boolean existsByUserId(String userId);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsByPhone(String phone);
}