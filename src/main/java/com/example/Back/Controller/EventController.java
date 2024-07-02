package com.example.Back.Controller;

import com.example.Back.Entity.Event;
import com.example.Back.Entity.dtos.CreateEventRequest;
import com.example.Back.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    @PreAuthorize("hasAuthority('agency:create')")
    public String addEvent(@RequestBody CreateEventRequest event){
        eventService.addEvent(event);
        return "success add event";
    }

    @GetMapping
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("by-agency")
    @PreAuthorize("hasAnyAuthority('agency:read')")
    public List<Event> getEventsByAgency() {
        return eventService.getEventsByAgencyId();
    }

    @GetMapping("/get")
    public Event getEvent(@RequestParam Integer id){
        return eventService.getEvent(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateEvent(@PathVariable Integer id, @RequestBody Event event){
        eventService.updateEvent(id, event);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('agency:delete') or hasAuthority('admin:delete')")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer id){
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
