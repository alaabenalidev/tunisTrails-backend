package com.example.Back.repository;

import com.example.Back.Entity.Event;
import com.example.Back.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    public List<Event> findAllByUser(User user);
    
}
