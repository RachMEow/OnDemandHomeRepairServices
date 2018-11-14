package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RegisterHomeOwnerActivity extends AppCompatActivity {

    private EditText firstName, lastName, address, postCode;
    private Button btnclear, btnRegister, btnCancel;

    private Account account = new Account();
    List<Account> accounts;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseAccounts = database.getReference("message");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_register);
    }


}
