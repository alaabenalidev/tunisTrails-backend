package com.example.Back.Service.Impl;

import com.example.Back.Entity.Reservation;
import com.example.Back.Entity.dtos.CreateReservationRequest;
import com.example.Back.Service.ReservationService;
import com.example.Back.repository.EventRepository;
import com.example.Back.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public void addReservation(CreateReservationRequest reservation) {

        Reservation newReservation = Reservation.builder()
                .event(this.eventRepository.findById(reservation.getEvent()).get())
                .reservationTime(reservation.getReservationTime())
                .adults(reservation.getAdults())
                .childrens(reservation.getChildrens())
                .numberOfParticipants(reservation.getNumberOfParticipants())
                .name(reservation.getName())
                .user(userServiceImpl.getAuthenticatedUser())
                .build();
        reservationRepository.save(newReservation);
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservation(Integer id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid reservation id: " + id));
    }

    @Override
    public void updateReservation(Integer id, Reservation reservation) {
        reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid reservation id: " + id));

        reservation.getIdReservation();
        reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Integer id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid reservation id: " + id));

        reservationRepository.delete(reservation);
    }
}
