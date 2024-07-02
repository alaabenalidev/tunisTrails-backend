package com.example.Back.Controller;

import com.example.Back.Entity.Ratings;
import com.example.Back.Service.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ratings")
public class RatingsController {

    @Autowired
    private RatingsService ratingsService;

    @PostMapping("/add")
    public String addRatings(@RequestBody Ratings ratings){
        ratingsService.addRatings(ratings);
        return "success add rating";
    }

    @GetMapping
    public List<Ratings> getRatings() {
        return ratingsService.getRatings();
    }

    @GetMapping("/get")
    public Ratings getRatings(@RequestParam Integer id){
        return ratingsService.getRatings(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateRatings(@PathVariable Integer id, @RequestBody Ratings ratings){
        ratingsService.updateRatings(id, ratings);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRatings(@PathVariable Integer id){
        ratingsService.deleteRatings(id);
        return ResponseEntity.noContent().build();
    }
}
