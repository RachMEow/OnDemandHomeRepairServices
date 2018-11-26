package com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider;

public class SPAvailableTime {

    private String id;
    private String spId;
    private DayOfWeek day;
    private int timeFrom;
    private int timeTo;

    public SPAvailableTime(){}

    public SPAvailableTime(String id, String spId, DayOfWeek day, int timeFrom, int timeTo){
        this.id = id;
        this.spId = spId;
        this.day = day;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public int getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(int timeFrom) {
        this.timeFrom = timeFrom;
    }

    public int getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(int timeTo) {
        this.timeTo = timeTo;
    }

    @Override
    public String toString() {
        return this.getDay() + " From " + String.valueOf(this.getTimeFrom()) + " to " + String.valueOf(this.getTimeTo());
    }
}
