package com.project.wish.repository;

import com.project.wish.domain.Otp;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp,Long> {

    Optional<Otp> findByUserId(String userId);
}
