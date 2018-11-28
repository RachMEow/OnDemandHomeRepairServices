package com.ondemandhomerepairservices.on_demandhomerepairservices.homeOwner;

import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.SPProvidedService;

public class HOBookedService extends SPProvidedService {

    private String hoBookedService_id;
    private String ho_id;

    public HOBookedService(){}

    public HOBookedService(String hoBookedService_id, String ho_id, String spProvidedService_id, String spCompanyName, String service_id, String serviceName, double hoursRate){
        super(spProvidedService_id, spCompanyName, service_id, serviceName, hoursRate);
        this.hoBookedService_id = hoBookedService_id;
        this.ho_id = ho_id;
    }

    public String getHoBookedService_id() {
        return hoBookedService_id;
    }

    public void setHoBookedService_id(String hoBookedService_id) {
        this.hoBookedService_id = hoBookedService_id;
    }

    public String getHo_id() {
        return ho_id;
    }

    public void setHo_id(String ho_id) {
        this.ho_id = ho_id;
    }
}
