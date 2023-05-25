package com.project.wish.repository;

import com.project.wish.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WishRepository extends JpaRepository<Wish, Long> {

}
