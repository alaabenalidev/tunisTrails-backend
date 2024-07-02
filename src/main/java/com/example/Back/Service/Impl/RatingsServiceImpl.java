package com.example.Back.Service.Impl;

import com.example.Back.Entity.Ratings;
import com.example.Back.Service.RatingsService;
import com.example.Back.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RatingsServiceImpl implements RatingsService {

    @Autowired
    private RatingsRepository ratingsRepository;

    @Override
    public void addRatings(Ratings ratings) {
        ratingsRepository.save(ratings);
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
