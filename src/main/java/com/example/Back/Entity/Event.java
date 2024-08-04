package com.example.Back.Entity;

import com.example.Back.Entity.enums.EType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @Lob
    @Column(length = 50000000)
    private byte[] image;

    private String title;

    @Enumerated(EnumType.STRING)
    private EType type;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Double latitude;

    private Double longitude;

    private Integer maxParticipants;

    private String location;

    @ManyToMany
    private List<Ratings> ratingsList;

    @ManyToMany
    @JsonProperty
    private List<EventComments> commentsList;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
//    @JsonIgnore
    private User user;

}
