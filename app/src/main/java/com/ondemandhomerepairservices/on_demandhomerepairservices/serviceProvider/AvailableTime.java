package com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider;

public class AvailableTime {
    private DayOfWeek _dayOfWeek;
    private TimeSlot _timeSlot;

    public AvailableTime(){

    }

    public AvailableTime(DayOfWeek dayOfWeek, TimeSlot timeSlot){
        this._dayOfWeek = dayOfWeek;
        this._timeSlot = timeSlot;
    }

    public DayOfWeek get_dayOfWeek() {
        return _dayOfWeek;
    }

    public void set_dayOfWeek(DayOfWeek _dayOfWeek) {
        this._dayOfWeek = _dayOfWeek;
    }

    public TimeSlot get_timeSlot() {
        return _timeSlot;
    }

    public void set_timeSlot(TimeSlot _timeSlot) {
        this._timeSlot = _timeSlot;
    }
}
