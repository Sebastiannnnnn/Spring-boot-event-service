package com.events.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Event {
    @Id
    public ObjectId _id;

    public String title;
    public String description;

    // Constructors
    public Event() {}

    public Event(ObjectId _id, String title, String description) {
        this._id = _id;
        this.title = title;
        this.description = description;
    }

    // ObjectId needs to be converted to string
    public String get_id() { return _id.toHexString(); }
    public void set_id(ObjectId _id) { this._id = _id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
