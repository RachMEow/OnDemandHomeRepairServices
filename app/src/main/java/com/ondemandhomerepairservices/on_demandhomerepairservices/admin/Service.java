package com.ondemandhomerepairservices.on_demandhomerepairservices.admin;

public class Service {
    private String service_id;
    private String _serviceName;
    private double _hoursRate;

    public Service(){}

    public Service(String id, String serviceName, double hoursRate){
        this.service_id = id;
        this._serviceName = serviceName;
        this._hoursRate = hoursRate;
    }

    public void set_id(String _id){this.service_id = _id;}

    public String get_id(){return this.service_id;}

    public void set_serviceName(String _serviceName) {
        this._serviceName = _serviceName;
    }

    public String get_serviceName(){
        return this._serviceName;
    }

    public void set_hoursRate(double _hoursRate) {
        this._hoursRate = _hoursRate;
    }

    public double get_hoursRate(){
        return _hoursRate;
    }

    @Override
    public String toString() {
        return this.get_serviceName() + " ($" + this.get_hoursRate() + "/hour)";
    }
}
