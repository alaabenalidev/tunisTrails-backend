package com.example.Back.Service;

import com.example.Back.Entity.Ratings;

import java.util.List;

public interface RatingsService {
    void addRatings(Ratings ratings);

    List<Ratings> getRatings();

    void updateRatings(Integer id, Ratings ratings);

    void deleteRatings(Integer id);

    Ratings getRatings(Integer id);
}
