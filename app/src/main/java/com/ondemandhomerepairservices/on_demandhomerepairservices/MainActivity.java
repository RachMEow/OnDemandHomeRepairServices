package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

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
