package com.ondemandhomerepairservices.on_demandhomerepairservices.accounts;

public class Admin extends Account{
    private String _firstName;
    private String _lastName;

    public Admin(){

    }

    public Admin(String id, String username, String password, String firstName, String lastName){
        super(id, username, password);
        _firstName = firstName;
        _lastName = lastName;
    }

    public String get_FirstName(){
        return _firstName;
    }

    public void set_FirstName(String firstName){
        _firstName = firstName;
    }

    public String get_LastName(){
        return _lastName;
    }

    public void set_LastName(String lastName){
        _lastName = lastName;
    }


}
