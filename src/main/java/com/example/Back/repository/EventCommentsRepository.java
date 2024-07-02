package com.example.Back.repository;

import com.example.Back.Entity.EventComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCommentsRepository extends JpaRepository<EventComments, Integer> {
}
