package com.events.controllers;

import com.events.constants.Constants;
import com.events.models.Event;
import com.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.bson.types.ObjectId;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventRepository repository;

    @Autowired
    private MongoTemplate template;

    @InitBinder
    public void customizeBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(dateFormatter, true));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Event> getEvents() {
        return repository.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Event> getEvents(
            @Valid @RequestParam Date dateStart,
            @Valid @RequestParam Date dateEnd
    ) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        Query query = new Query();
        query.addCriteria(
                Criteria
                        .where("eventDate.dateStart")
                        .gte(sdf.format(dateStart))
                        .lte(sdf.format(dateEnd))
        );

        return template.find(query, Event.class);
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
