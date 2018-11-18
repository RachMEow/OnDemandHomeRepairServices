package com.ondemandhomerepairservices.on_demandhomerepairservices.accounts;

import android.widget.ArrayAdapter;

import com.ondemandhomerepairservices.on_demandhomerepairservices.admin.Service;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.AvailableTime;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.DayOfWeek;

import java.util.ArrayList;

public class ServiceProvider extends Account{

    private String _companyName;
    private String _address;
    private String _phoneNum;
    private String _generalDescription;// = "Nothing here";
    private boolean _licensed;
    private ArrayList<Service> _providedServices = new ArrayList<>();
    private ArrayList<AvailableTime> _availableTimes = new ArrayList<AvailableTime>();

    public ServiceProvider(){}

    public ServiceProvider(String id, String username, String password, String companyName, String address, String phoneNum, String generalDescription, boolean licensed){
        super(id, username, password);
        this._companyName = companyName;
        this._address = address;
        this._phoneNum = phoneNum;
        this._generalDescription = generalDescription;
        this._licensed = licensed;
    }

    public ServiceProvider(String id, ArrayList<AvailableTime> availableTimes){
        super(id);
        this._availableTimes = availableTimes;
    }

    public String get_companyName() {
        return _companyName;
    }

    public void set_companyName(String _companyName) {
        this._companyName = _companyName;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_phoneNum() {
        return _phoneNum;
    }

    public void set_phoneNum(String _phoneNum) {
        this._phoneNum = _phoneNum;
    }

    public String get_generalDescription() {
        return _generalDescription;
    }

    public void set_generalDescription(String _generalDescription) {
        this._generalDescription = _generalDescription;
    }

    public boolean is_licensed() {
        return _licensed;
    }

    public void set_licensed(boolean _licensed) {
        this._licensed = _licensed;
    }

    public ArrayList<AvailableTime> getAvailableTimes() {
        return _availableTimes;
    }

    public void setAvailableTimes(ArrayList<AvailableTime> availableTimes) {
        this._availableTimes = availableTimes;
    }
}
