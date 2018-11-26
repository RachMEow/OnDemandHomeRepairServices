package com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider;

import com.ondemandhomerepairservices.on_demandhomerepairservices.admin.Service;

public class SPProvidedService extends Service {

    private String id;
    private String spId;

    public SPProvidedService(){}

    public SPProvidedService( String id, String spId, String serviceId, String serviceName, double hoursRate){
        super(serviceId, serviceName, hoursRate);
        this.id = id;
        this.spId = spId;
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

//    @Override
//    public String toString() {
//        return super.toString();
//    }
}
