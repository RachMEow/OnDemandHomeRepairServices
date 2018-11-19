package com.ondemandhomerepairservices.on_demandhomerepairservices.accounts;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Account {
    private String _id;
    private String _username;
    private String _password;

    public Account() {}

    public Account(String id){
        _id = id;
    }

    public Account(String id, String username, String password) {
        _id = id;
        _username = username;
        _password = password;
    }

    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
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



}



