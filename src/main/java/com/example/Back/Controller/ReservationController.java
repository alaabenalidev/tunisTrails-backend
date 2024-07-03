package com.example.Back.Controller;

import com.example.Back.Entity.Reservation;
import com.example.Back.Entity.dtos.CreateReservationRequest;
import com.example.Back.Service.Impl.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Reservation")
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationService;

    @PostMapping("/add")
    public ResponseEntity<Object> addReservation(@RequestBody CreateReservationRequest reservation) {
        reservationService.addReservation(reservation);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Reservation> getReservations() {
        return reservationService.getReservations();
    }

    @GetMapping("/by-user")
    public List<Reservation> getReservationsByUser() {
        return reservationService.getReservationsByUser();
    }

    @GetMapping("/get")
    public Reservation getReservation(@RequestParam Integer id) {
        return reservationService.getReservation(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateReservation(@PathVariable Integer id, @RequestBody Reservation reservation) {
        reservationService.updateReservation(id, reservation);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
