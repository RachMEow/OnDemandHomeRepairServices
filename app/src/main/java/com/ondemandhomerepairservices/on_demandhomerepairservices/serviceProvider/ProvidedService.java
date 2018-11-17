package com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider;

import com.ondemandhomerepairservices.on_demandhomerepairservices.admin.Service;

import java.util.List;

public class ProvidedService extends Service {
    private boolean _availability = true;

    //TODO: not sure how to generate this, maybe in this class or in the activity that uses it
    private DayOfWeek _dayOfWeek;
    private AvailableTime _availableTime;

    public ProvidedService(){

    }

    public ProvidedService(boolean availability, DayOfWeek dayOfWeek, AvailableTime availableTime){
        this._availability = availability;
        this._dayOfWeek = dayOfWeek;
        this._availableTime = availableTime;
    }

    public boolean is_availability() {
        return _availability;
    }

    public void set_availability(boolean _availability) {
        this._availability = _availability;
    }

    public DayOfWeek get_dayOfWeek() {
        return _dayOfWeek;
    }

    public void set_dayOfWeek(DayOfWeek _dayOfWeek) {
        this._dayOfWeek = _dayOfWeek;
    }

    public AvailableTime get_availableTime() {
        return _availableTime;
    }

    public void set_availableTime(AvailableTime _availableTime) {
        this._availableTime = _availableTime;
    }
}
