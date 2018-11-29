package com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider;

import com.ondemandhomerepairservices.on_demandhomerepairservices.admin.Service;

public class SPProvidedService extends Service {

    private String spProvidedService_id;
    private String spId;
    private String spCompanyName;

    public SPProvidedService(){}

//    public SPProvidedService( String id, String spId, String serviceId, String serviceName, double hoursRate){
//        super(serviceId, serviceName, hoursRate);
//        this.id = id;
//        this.spId = spId;
//    }

    public SPProvidedService( String spProvidedService_id, String spId, String spCompanyName, String serviceId, String serviceName, double hoursRate){
        super(serviceId, serviceName, hoursRate);
        this.spProvidedService_id = spProvidedService_id;
        this.spId = spId;
        this.spCompanyName = spCompanyName;
    }

    public String getSpProvidedService_id() {
        return spProvidedService_id;
    }

    public void setSpProvidedService_id(String spProvidedService_id) {
        this.spProvidedService_id = spProvidedService_id;
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
