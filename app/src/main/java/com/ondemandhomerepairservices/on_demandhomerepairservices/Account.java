package com.ondemandhomerepairservices.on_demandhomerepairservices;

public class Account {
    private String _username;
    private String _password;
    private String _firstName;
    private String _lastName;
    private String _role;

    public Account() {

    }

    public Account(String username, String password, String firstName, String lastName, String role) {
        _username = username;
        _password = password;
        _firstName = firstName;
        _lastName = lastName;
        _role = role;
    }

    public void set_username(String username) {
        _username = username;
    }

    public String get_username() {
        return _username;
    }

    public void set_password(String password) {
        _password = password;
    }

    public String get_password() {
        return _password;
    }

    public void set_firstName(String firstName) {
        _firstName = firstName;
    }

    public String get_firstName() {
        return _firstName;
    }

    public void set_lastName(String lastName) {
        _lastName = lastName;
    }

    public String get_lastName() {
        return _lastName;
    }

    public void set_role(String role) {
        _role = role;
    }

    public String get_role() {
        return _role;
    }

}



