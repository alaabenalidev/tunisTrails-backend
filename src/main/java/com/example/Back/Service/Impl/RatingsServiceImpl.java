package com.example.Back.Service.Impl;

import com.example.Back.Entity.Event;
import com.example.Back.Entity.Ratings;
import com.example.Back.Entity.dtos.AddRatingRequest;
import com.example.Back.Service.RatingsService;
import com.example.Back.repository.EventRepository;
import com.example.Back.repository.RatingsRepository;
import com.example.Back.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RatingsServiceImpl implements RatingsService {

    @Autowired
    private RatingsRepository ratingsRepository;
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public void addRatings(AddRatingRequest ratings) {
        User user = this.userServiceImpl.getAuthenticatedUser();
        Event event = this.eventRepository.findById(ratings.getEventId()).get();
        Ratings rating = Ratings.builder()
                .user(user)
                .event(event)
                .ratingValue(ratings.getStars())
                .build();
        ratingsRepository.save(rating);
    }

    @Override
    public List<Ratings> getRatings() {
        return ratingsRepository.findAll();
    }

    @Override
    public Ratings getRatings(Integer id) {
        return ratingsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid rating id: " + id));
    }

    @Override
    public void updateRatings(Integer id, Ratings ratings) {
        ratingsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid rating id: " + id));

        ratings.setIdRatings(id);
        ratingsRepository.save(ratings);
    }

    @Override
    public void deleteRatings(Integer id) {
        Ratings ratings = ratingsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid rating id: " + id));

        ratingsRepository.delete(ratings);
    }
}
