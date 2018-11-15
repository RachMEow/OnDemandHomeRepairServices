package com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider;

import com.ondemandhomerepairservices.on_demandhomerepairservices.admin.Service;

import java.util.List;

public class ProvidedService extends Service {
    private boolean _availability = true;

    //TODO: not sure how to generate this, maybe in this class or in the activity that uses it
    private List<AvailableTime> _availableTimes;

    public ProvidedService(){

    }

    public ProvidedService(boolean availability, List<AvailableTime> availableTimes){
        this._availability = availability;
        this._availableTimes = availableTimes;
    }

    public boolean is_availability() {
        return _availability;
    }

    public void set_availability(boolean _availability) {
        this._availability = _availability;
    }

}
