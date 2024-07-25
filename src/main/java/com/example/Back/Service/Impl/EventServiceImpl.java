package com.example.Back.Service.Impl;

import com.example.Back.Entity.Event;
import com.example.Back.Entity.dtos.CreateEventRequest;
import com.example.Back.Entity.enums.EType;
import com.example.Back.Service.EventService;
import com.example.Back.repository.EventRepository;
import com.example.Back.security.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
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
        ev.setLocation(event.getLocation());
        ev.setImage(event.getImage());
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

    @Override
    public List<Event> searchByKeyword(String keyword, String type) {
        if (type != null && type != "" && type.length() > 0) {
            EType eType = EType.valueOf(type);
            return eventRepository.findByLocationIsContainingIgnoreCaseOrTitleContainingIgnoreCaseAndType(keyword, keyword, eType);
        }

        return eventRepository.findByLocationIsContainingIgnoreCaseOrTitleContainingIgnoreCase(keyword, keyword);

    }
}
