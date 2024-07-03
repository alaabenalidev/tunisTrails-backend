package com.example.Back.Service.Impl;

import com.example.Back.Entity.Event;
import com.example.Back.Entity.EventComments;
import com.example.Back.Entity.dtos.AddEventCommentRequest;
import com.example.Back.Service.EventCommentsService;
import com.example.Back.repository.EventCommentsRepository;
import com.example.Back.repository.EventRepository;
import com.example.Back.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EventCommentsServiceImpl implements EventCommentsService {
    @Autowired
    private EventCommentsRepository eventCommentsRepository;
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public void addEventComments(AddEventCommentRequest eventComments) {
        Event event = this.eventRepository.findById(eventComments.getEventId()).get();
        User user = this.userServiceImpl.getAuthenticatedUser();
        EventComments eventComment = EventComments.builder()
                .event(event)
                .user(user)
                .CommentText(eventComments.getComment())
                .build();
        eventCommentsRepository.save(eventComment);
    }

    @Override
    public List<EventComments> getEventComments() {
        return eventCommentsRepository.findAll();
    }

    @Override
    public EventComments getEventComments(Integer id) {
        EventComments eventComments = eventCommentsRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid comment id " + id));

        return eventComments;
    }

    @Override
    public void updateEventComments(Integer id, EventComments eventComments) {
        // Check whether the comment exists in the database or not
        eventCommentsRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid comment id " + id));

        eventComments.setIdEventComments(id);
        eventCommentsRepository.save(eventComments);
    }

    @Override
    public void deleteEventComments(Integer id) {
        // Check whether the comment exists in the database or not
        EventComments eventComments = eventCommentsRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid comment id " + id));

        eventCommentsRepository.delete(eventComments);
    }

}
