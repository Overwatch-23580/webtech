package com.project.lifebank.service;

import com.project.lifebank.dto.EventDto;
import com.project.lifebank.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<EventDto> getAllEvents();
//    TODO: change this to EventDto
    Optional<Event> getEventById(int id);
    void saveEvent(EventDto eventDto);
    void deleteEventById(int id);

    void updateEvent(EventDto eventDto);
//    Optional<Event> getEventByEmail(String email);
}
