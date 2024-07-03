package com.example.Back.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.Back.security.user.User;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ratings {

    @Id
    @GeneratedValue
    private Integer idRatings;

    private int ratingValue;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "IdEvent")
    private Event event;


}
