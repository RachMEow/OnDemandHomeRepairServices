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

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Spinner registerAs;
    private EditText _username, _password, _firstName, _lastName;
    private Button btnClear, btnRegister;

    private Account account = new Account();
//    private FirebaseAuth auth;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseAccount = database.getReference("message");

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
        _firstName = (EditText) findViewById(R.id.editTextFirstName);
        _lastName = (EditText)findViewById(R.id.editTextLastName);
        btnClear = (Button)findViewById(R.id.buttonClear);
        btnRegister = (Button)findViewById(R.id.buttonRegister);

        databaseAccount = FirebaseDatabase.getInstance().getReference("Account");

        btnClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                reset();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = _username.getText().toString().trim();
                String password = _password.getText().toString().trim();
                String firstName = _firstName.getText().toString().trim();
                String lastName = _lastName.getText().toString().trim();
                String role = registerAs.getSelectedItem().toString();

                if(TextUtils.isEmpty(username)){
                    Toast.makeText(getApplicationContext(), "Enter username", Toast.LENGTH_SHORT).show();
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

                if(TextUtils.isEmpty(firstName)){
                    Toast.makeText(getApplicationContext(), "Enter first name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(lastName)){
                    Toast.makeText(getApplicationContext(), "Enter last name", Toast.LENGTH_SHORT).show();
                    return;
                }

                //create account
                account = new Account(username, password, firstName, lastName, role);
                startActivity(new Intent(RegisterActivity.this, RegisterSuccess.class));
                finish();
            }
        });


    }

    public void reset(){
        _username.getText().clear();
        _firstName.getText().clear();
        _lastName.getText().clear();
        _password.getText().clear();
        return;

    }

    public void OnMainButton(View view){

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivityForResult(intent, 0);
    }
}
