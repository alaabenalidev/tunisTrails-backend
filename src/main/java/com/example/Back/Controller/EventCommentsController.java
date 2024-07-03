package com.example.Back.Controller;
import com.example.Back.Entity.EventComments;
import com.example.Back.Entity.dtos.AddEventCommentRequest;
import com.example.Back.Service.EventCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/EventComments")

public class EventCommentsController {
    @Autowired
    private EventCommentsService eventCommentsService;

    @PostMapping("/add")
    public String addEventComments(@RequestBody AddEventCommentRequest eventComments){
        eventCommentsService.addEventComments(eventComments);

        return "success add comment";
    }

    @GetMapping
    public List<EventComments> getEventComments() {
        return eventCommentsService.getEventComments();
    }

    @GetMapping("/get")
    public EventComments getEventComments(@RequestParam Integer id){
        return eventCommentsService.getEventComments(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateEventComments(@PathVariable Integer id, @RequestBody EventComments eventComments){
        eventCommentsService.updateEventComments(id, eventComments);

        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEventComments(@PathVariable Integer id){
        eventCommentsService.deleteEventComments(id);

        return ResponseEntity.noContent().build();
    }
}
