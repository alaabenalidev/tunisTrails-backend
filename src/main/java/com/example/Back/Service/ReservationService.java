package com.example.Back.Service;

import com.example.Back.Entity.Reservation;
import com.example.Back.Entity.dtos.CreateReservationRequest;

import java.util.List;

public interface ReservationService {

    void addReservation(CreateReservationRequest reservation);

    List<Reservation> getReservations();

    Reservation getReservation(Integer id);

    void updateReservation(Integer id, Reservation reservation);

    void deleteReservation(Integer id);
}
