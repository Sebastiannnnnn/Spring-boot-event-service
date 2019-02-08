package com.events.repository;

import com.events.models.Event;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {
    Event findBy_id(ObjectId _id);

    List<Event> getEvents();

    List<Event> getEventsByDate(String dateStart, String dateEnd);
}