package com.ondemandhomerepairservices.on_demandhomerepairservices.accounts;

public class HomeOwner extends Account{

    private String _firstName;
    private String _lastName;
    private String _address;
    private String _postalCode;

    public HomeOwner(){}

    public HomeOwner(String id, String username, String password, String firstName, String lastName, String address, String postalCode){
        super(id, username, password);
        this._firstName = firstName;
        this._lastName = lastName;
        this._address = address;
        this._postalCode = postalCode;

    }

    public String get_firstName() {
        return _firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String get_lastName() {
        return _lastName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_postalCode() {
        return _postalCode;
    }

    public void set_postalCode(String _postalCode) {
        this._postalCode = _postalCode;
    }
}
