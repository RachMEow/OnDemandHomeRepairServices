package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Spinner registerAs;
    private EditText _username, _password;// _firstName, _lastName;
    private Button btnReset1, btnRegister;

    private Account account = new Account();
    List<Account> accounts;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseAccounts = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerAs = findViewById(R.id.spinnerRegisterAs);
        //create a list of items for the spinner.
        String[] items = new String[]{"Admin", "Service Provider", "Home Owner"};

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        registerAs.setAdapter(adapter);

        //Get Firebase auth instance
//        auth = FirebaseAuth.getInstance();

        _username = (EditText)findViewById(R.id.editTextUsername);
        _password = (EditText)findViewById(R.id.editTextPassword);
//        _firstName = (EditText) findViewById(R.id.editTextFirstName);
//        _lastName = (EditText)findViewById(R.id.editTextLastName);
        btnReset1 = (Button)findViewById(R.id.buttonReset1);
        btnRegister = (Button)findViewById(R.id.buttonNext1);

        databaseAccounts = FirebaseDatabase.getInstance().getReference("accounts");

        accounts = new ArrayList<>();

        btnReset1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                reset();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String id;
                String username = trimString(_username.getText().toString());
                String password = trimString(_password.getText().toString());
//                String firstName = trimString(_firstName.getText().toString());
//                String lastName = trimString(_lastName.getText().toString());
                String role = registerAs.getSelectedItem().toString();

                Pattern p1 = Pattern.compile("[^a-z0-9_]", Pattern.CASE_INSENSITIVE);
                Pattern p2 = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
                boolean un = p1.matcher(username).find();
//                boolean fn = p2.matcher(firstName).find();
//                boolean ln = p2.matcher(lastName).find();

                //Validate fields
                if(un) {
                    Toast.makeText(getApplicationContext(), "User name can only contain letter, number and underscore", Toast.LENGTH_SHORT).show();
                    return;
                }

//                if(fn) {
//                    Toast.makeText(getApplicationContext(), "First name can only contains letter", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if(ln) {
//                    Toast.makeText(getApplicationContext(), "Last name can only contains letter", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                if(TextUtils.isEmpty(username)) {
                    Toast.makeText( getApplicationContext(), "Enter username", Toast.LENGTH_SHORT ).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length() < 6){
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

//                if(TextUtils.isEmpty(firstName)){
//                    Toast.makeText(getApplicationContext(), "Enter first name", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if(TextUtils.isEmpty(lastName)){
//                    Toast.makeText(getApplicationContext(), "Enter last name", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                String id = databaseAccounts.push().getKey();

                //create an account object
//                account = new Account(id, username, password, firstName, lastName, role);
                account = new Account(id, username, password, role);

                //saving the account
                databaseAccounts.child(id).setValue(account);

                finish();
                startActivity(new Intent(RegisterActivity.this, RegisterSuccess.class));

            }
        });


    }

    //Reset fields
    public void reset(){
        _username.getText().clear();
//        _firstName.getText().clear();
//        _lastName.getText().clear();
        _password.getText().clear();
        return;

    }

    public String trimString(String e) {
        return e.trim();
    }

//    public void OnMainButton(View view){
//
//        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//        startActivityForResult(intent, 0);
//    }
}
