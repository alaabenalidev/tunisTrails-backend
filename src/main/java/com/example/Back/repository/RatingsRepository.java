package com.example.Back.repository;

import com.example.Back.Entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingsRepository extends JpaRepository<Ratings, Integer> {
}
