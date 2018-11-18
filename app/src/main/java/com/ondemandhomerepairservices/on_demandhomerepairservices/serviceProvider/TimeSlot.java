package com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider;

public enum TimeSlot {
    NINE_TO_TWELVE("9 to 12"), TWO_TO_FIVE("2 to 5");

    private final String timeSlot;

    private TimeSlot(String value){
        timeSlot = value;
    }

    public String getTimeSlot(){
        return timeSlot;
    }

}
