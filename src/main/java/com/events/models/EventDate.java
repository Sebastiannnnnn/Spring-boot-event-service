package com.events.models;

public class EventDate {
    private String dateStart;
    private String dateEnd;

    public EventDate() {}

    public EventDate(String dateStart, String dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
