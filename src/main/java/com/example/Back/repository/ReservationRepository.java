package com.example.Back.repository;

import com.example.Back.Entity.Event;
import com.example.Back.Entity.Reservation;
import com.example.Back.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findAllByUser(User user);
    List<Reservation> findAllByEvent(Event event);
}
