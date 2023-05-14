package com.project.lifebank.service;

import com.project.lifebank.dto.EventDto;
import com.project.lifebank.model.Event;
import com.project.lifebank.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepo;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepo = eventRepository;
    }

    @Override
    public List<EventDto> getAllEvents() {
        List<Event> events = eventRepo.findAll();
        return events.stream()
                .map(this::mapToEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Event> getEventById(int id) {
       return eventRepo.findById(id);
    }


    private EventDto mapToEventDto(Event event){
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setTitle(event.getTitle());
        eventDto.setLocation(event.getLocation());
        eventDto.setDescription(event.getDescription());
        eventDto.setStartingDate(event.getStartingDate());
        eventDto.setEndingDate(event.getEndingDate());
        eventDto.setStartTime(event.getStartTime());
        eventDto.setEndingTime(event.getEndingTime());
        eventDto.setEventImage(event.getEventImage());
        return eventDto;
    }

    @Override
    public void saveEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepo.save(event);
    }

    private Event mapToEvent(EventDto eventDto){
        Event event = new Event();
        event.setId(eventDto.getId());
        event.setTitle(eventDto.getTitle());
        event.setLocation(eventDto.getLocation());
        event.setDescription(eventDto.getDescription());
        event.setStartingDate(eventDto.getStartingDate());
        event.setEndingDate(eventDto.getEndingDate());
        event.setStartTime(eventDto.getStartTime());
        event.setEndingTime(eventDto.getEndingTime());
        event.setEventImage(eventDto.getEventImage());
        return event;
    }


    @Override
    public void deleteEventById(int id) {
        eventRepo.deleteById(id);
    }

//    @Override
//    public void updateEvent(EventDto eventDto) {
//        Optional<Event> existingPortfolio = eventRepo.findById(eventDto.getId());
//
//        if (existingPortfolio.isPresent()) {
//            Event updatedPortfolio = mapToEvent(eventDto);
//            updatedPortfolio.setId(existingPortfolio.get().getId()); // set the same ID as the existing portfolio
//            eventRepo.delete(existingPortfolio.get());// delete the existing portfolio
//            eventRepo.save(updatedPortfolio); // save the updated portfolio with the same ID
//        } else {
//            // handle the case where the portfolio with the given ID doesn't exist
//            System.out.println("You don't exist");
//        }
//    }
@Override
public void updateEvent(EventDto eventDto) {
    Event existingEvent = eventRepo.findById(eventDto.getId())
            .orElseThrow(() -> new EntityNotFoundException("Event not found"));
    Event updatedEvent = mapToEvent(eventDto);
    updatedEvent.setId(existingEvent.getId());
    eventRepo.save(updatedEvent);
}
}



