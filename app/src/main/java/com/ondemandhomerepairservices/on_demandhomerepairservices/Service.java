package com.ondemandhomerepairservices.on_demandhomerepairservices;

public class Service {
    private String _serviceName;
    private double _hoursRate;

    public Service(){}

    public Service(String serviceName, double hoursRate){
        this._serviceName = serviceName;
        this._hoursRate = hoursRate;
    }

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
        return get_hoursRate();
    }
}
