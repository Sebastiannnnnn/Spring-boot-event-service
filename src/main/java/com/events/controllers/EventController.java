package com.events.controllers;

import com.events.models.Event;
import com.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.bson.types.ObjectId;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Event> getEvents() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Event getEventById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyEventById(@PathVariable("id") ObjectId id, @Valid @RequestBody Event event) {
        event.set_id(id);
        repository.save(event);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Event createEvent(@Valid @RequestBody Event event) {
        event.set_id(ObjectId.get());
        repository.save(event);
        return event;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}
