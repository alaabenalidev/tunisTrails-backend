package com.example.Back.Service;

import com.example.Back.Entity.EventComments;

import java.util.List;

public interface EventCommentsService {
    void addEventComments(EventComments eventComments);

    List<EventComments> getEventComments();

    EventComments getEventComments(Integer id);

    void updateEventComments(Integer id, EventComments eventComments);

    void deleteEventComments(Integer id);
}
