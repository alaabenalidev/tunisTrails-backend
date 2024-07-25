package com.example.Back.Controller;

import com.example.Back.Entity.Event;
import com.example.Back.Entity.dtos.CreateEventRequest;
import com.example.Back.Entity.enums.EType;
import com.example.Back.Service.EventService;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@Slf4j
public class EventController {

    @Autowired
    private EventService eventService;

//    @PostMapping
//    @PreAuthorize("hasAuthority('agency:create')")
//    public String addEvent(@RequestBody CreateEventRequest event){
//        eventService.addEvent(event);
//        return "success add event";
//    }

    @PostMapping
    @PreAuthorize("hasAuthority('agency:create')")
    public String addEvent(@RequestParam String title,
                           @RequestParam String type,
                           @RequestParam String ville,
                           @RequestParam String description,
                           @RequestParam LocalDateTime startDate,
                           @RequestParam LocalDateTime endDate,
                           @RequestParam Double latitude,
                           @RequestParam Double longitude,
                           @RequestParam Integer maxParticipants,
                           @RequestParam MultipartFile image) throws IOException {
        CreateEventRequest event = CreateEventRequest.builder()
                .title(title)
                .description(description)
                .type(EType.valueOf(type))
                .startDate(startDate)
                .location(ville)
                .endDate(endDate)
                .latitude(latitude)
                .longitude(longitude)
                .maxParticipants(maxParticipants)
                .image(image.getBytes())
                .build();
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

    @GetMapping("search")
    public List<Event> searchEvent(@Nullable @RequestParam String keyword,@Nullable @RequestParam String type){
        return eventService.searchByKeyword(keyword,type);
    }
}
