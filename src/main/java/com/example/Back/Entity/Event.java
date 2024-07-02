package com.example.Back.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import com.example.Back.security.user.User;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    private Integer idEvent;

    private String title;

    private String type;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Double latitude;

    private Double longitude;

    private Integer maxParticipants;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

}
