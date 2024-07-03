package com.example.Back.Service;

import com.example.Back.Entity.Ratings;
import com.example.Back.Entity.dtos.AddRatingRequest;

import java.util.List;

public interface RatingsService {
    void addRatings(AddRatingRequest ratings);

    List<Ratings> getRatings();

    void updateRatings(Integer id, Ratings ratings);

    void deleteRatings(Integer id);

    Ratings getRatings(Integer id);
}
