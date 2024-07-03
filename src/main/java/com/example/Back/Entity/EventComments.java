package com.example.Back.Entity;


import com.example.Back.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventComments {


    @Id
    @GeneratedValue
    private Integer IdEventComments;

    private String CommentText;


    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

}
