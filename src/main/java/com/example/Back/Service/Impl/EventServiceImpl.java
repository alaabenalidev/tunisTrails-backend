package com.example.Back.Service.Impl;

import com.example.Back.Entity.Event;
import com.example.Back.Entity.dtos.CreateEventRequest;
import com.example.Back.Service.EventService;
import com.example.Back.repository.EventRepository;
import com.example.Back.security.auth.AuthenticationService;
import com.example.Back.security.user.User;
import com.example.Back.security.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void addEvent(CreateEventRequest event) {
        User user = userService.getAuthenticatedUser();
        Event ev = new Event();
        ev.setUser(user);
        ev.setDescription(event.getDescription());
        ev.setEndDate(event.getEndDate());
        ev.setStartDate(event.getStartDate());
        ev.setLongitude(event.getLongitude());
        ev.setLatitude(event.getLatitude());
        ev.setTitle(event.getTitle());
        ev.setType(event.getType());
        ev.setMaxParticipants(event.getMaxParticipants());
        eventRepository.save(ev);
    }

    @Override
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getEventsByAgencyId() {
        User user = userService.getAuthenticatedUser();
        return this.eventRepository.findAllByUser(user);
    }

    @Override
    public Event getEvent(Integer id) {
        return eventRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid event id " + id));
    }

    @Override
    public void updateEvent(Integer id, Event event) {
        // Check whether the event is in the database or not
        eventRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid event id " + id));

        event.setIdEvent(id);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Integer id) {
        // Check whether the event is in the database or not
        Event event = eventRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid event id " + id));

        eventRepository.delete(event);
    }
}
