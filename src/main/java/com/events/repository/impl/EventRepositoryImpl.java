package com.events.repository.impl;

import com.events.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class EventRepositoryImpl {
    private final MongoOperations operations;

    @Autowired
    public EventRepositoryImpl(MongoOperations operations) {
        this.operations = operations;
    }

    public List<Event> getEvents() {
        return operations.findAll(Event.class);
    }

    public List<Event> getEventsByDate( String dateStart, String dateEnd) {
        Query query = new Query();
        query.addCriteria(Criteria
                .where("eventDate.dateStart")
                .gte(dateStart)
                .lte(dateEnd)
        );

        return operations.find(query, Event.class);
    }
}
