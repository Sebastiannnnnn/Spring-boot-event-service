package com.events.controllers;

import com.events.constants.Constants;
import com.events.models.Event;
import com.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.bson.types.ObjectId;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/events")
@SuppressWarnings("unused")
public class EventController {
    @Autowired
    private EventRepository repository;

    private SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);

    /**
     * Init binder for the date format
     * @param binder        bind dateFormat
     */
    @InitBinder
    public void customizeBinding(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(df, true));
    }

    /**
     * Get all the events
     * @return              List of events
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = Constants.APPLICATION_JSON)
    public List<Event> getEvents() {
        return repository.getEvents();
    }

    /**
     * Get list of events with dateStart and dateEnd
     * @param dateStart     ISOString converted to Date
     * @param dateEnd       ISOString converted to Date
     * @return              List of events
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = Constants.APPLICATION_JSON)
    public List<Event> getEvents(
            @Valid @RequestParam Date dateStart,
            @Valid @RequestParam Date dateEnd
    ) {
        return repository.getEventsByDate(df.format(dateStart), df.format(dateEnd));
    }

    /**
     * Get event by id
     * @param id            targeted unique identifier
     * @return event        event that holds the id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = Constants.APPLICATION_JSON)
    public Event getEventById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }

    /**
     * Create new event and generate id
     * @param event         event to be created
     * @return              created event
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = Constants.APPLICATION_JSON)
    public Event createEvent(@Valid @RequestBody Event event) {
        event.set_id(ObjectId.get());
        repository.save(event);
        return event;
    }

    /**
     * Update targeted event
     * @param id            targeted unique identifier
     * @param event         event in question
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyEventById(@PathVariable("id") ObjectId id, @Valid @RequestBody Event event) {
        event.set_id(id);
        repository.save(event);
    }

    /**
     * Delete event by id
     * @param id            targeted id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}
