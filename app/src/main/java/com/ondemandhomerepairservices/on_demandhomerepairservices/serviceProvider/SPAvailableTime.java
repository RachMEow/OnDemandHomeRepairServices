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

    //TODO: setter and getting methods for SPAvailableTime
}
