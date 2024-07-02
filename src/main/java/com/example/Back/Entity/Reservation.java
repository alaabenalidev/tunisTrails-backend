package com.example.Back.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import com.example.Back.security.user.User;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue
    private Integer idReservation;

    private String name;

    @Min(value = 1)
    private int numberOfParticipants;
    @Min(value = 0)
    private int adults;
    @Min(value = 0)
    private int childrens;

    private LocalDateTime reservationTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "IdEvent")
    private Event event;


}