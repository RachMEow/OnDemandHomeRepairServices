package com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider;

import com.ondemandhomerepairservices.on_demandhomerepairservices.admin.Service;

public class SPProvidedService extends Service {

    private String id;
    private String spId;
    private String spCompanyName;

    public SPProvidedService(){}

//    public SPProvidedService( String id, String spId, String serviceId, String serviceName, double hoursRate){
//        super(serviceId, serviceName, hoursRate);
//        this.id = id;
//        this.spId = spId;
//    }

    public SPProvidedService( String id, String spId, String spCompanyName, String serviceId, String serviceName, double hoursRate){
        super(serviceId, serviceName, hoursRate);
        this.id = id;
        this.spId = spId;
        this.spCompanyName = spCompanyName;
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

    public String getSpCompanyName() {
        return spCompanyName;
    }

    public void setSpCompanyName(String spCompanyName) {
        this.spCompanyName = spCompanyName;
    }

    //    @Override
//    public String toString() {
//        return super.toString();
//    }
}
