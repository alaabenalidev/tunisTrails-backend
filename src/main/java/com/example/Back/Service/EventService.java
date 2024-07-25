package com.example.Back.Service;

import com.example.Back.Entity.Event;
import com.example.Back.Entity.dtos.CreateEventRequest;
import com.example.Back.Entity.enums.EType;

import java.util.List;

public interface EventService {
    void addEvent(CreateEventRequest event);

    List<Event> getEvents();
    List<Event> getEventsByAgencyId();

    Event getEvent(Integer id);

    void updateEvent(Integer id, Event event);

    void deleteEvent(Integer id);

    List<Event> searchByKeyword(String keyword, String type);

}
