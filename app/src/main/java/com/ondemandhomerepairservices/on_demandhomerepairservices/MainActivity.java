package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseProducts = database.getReference("message");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText username = (EditText)findViewById(R.id.editTextUsername);
        final EditText password = (EditText)findViewById(R.id.editTextPassword);
        final Button blogin = (Button) findViewById(R.id.buttonLogin);
    }

    public void OnsetRegisterButton(View view){

        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnsetLoginButton(View view){
        Intent intent = new Intent(getApplicationContext(),MenuPage.class);
        startActivityForResult(intent, 0);
    }



}
