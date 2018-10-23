package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText _username, _password;
    private Button btnLogin, btnRegister;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseAccounts = database.getReference("message");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _username = (EditText)findViewById(R.id.editTextUsername);
        _password = (EditText)findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnRegister = (Button) findViewById(R.id.buttonRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = _username.getText().toString().trim();
                accountExists(username);
            }
        });
    }

//
//    public void OnsetRegisterButton(View view){
//
//        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
//        startActivityForResult(intent, 0);
//    }
//
//    public void OnsetLoginButton(View view){
//        Intent intent = new Intent(getApplicationContext(),MenuPage.class);
//        startActivityForResult(intent, 0);
//    }

    private String getRole(){
        String role = "Admin";

        return role;
    }


    //TODO: check if account exists in the FireBase
    private void accountExists(final String username){

        //getting the specific product reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("accounts").child(username);

        if(validate()){
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot data: dataSnapshot.getChildren())
                    if(dataSnapshot.child(username).exists()){

                        startActivity(new Intent(MainActivity.this, LoginAdmin.class));
                        //TODO: according to different roles, go to different interfaces. Use switch or if

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    //Valid the inputs are not empty
    private boolean validate(){
        String username = _username.getText().toString().trim();
        String password = _password.getText().toString().trim();

        //Validate fields
        if(TextUtils.isEmpty(username)){
            Toast.makeText(getApplicationContext(), "Enter username", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }



}
