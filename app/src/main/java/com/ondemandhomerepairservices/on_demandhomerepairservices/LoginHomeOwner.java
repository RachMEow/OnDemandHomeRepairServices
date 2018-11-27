package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class LoginHomeOwner extends AppCompatActivity {

    Button buttonLogout,buttonBookedService,buttonServiceProvided;
    TextView firstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_home_owner );

        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        firstName = (TextView) findViewById(R.id.textViewFirstName);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        firstName.setText(getIntent().getStringExtra("USERNAME"));

        buttonBookedService = (Button) findViewById(R.id.buttonListOfBookedService);
        buttonBookedService.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent (LoginHomeOwner.this, HomeOwnerBookedServices.class) );
            }
        } );

        buttonServiceProvided = (Button) findViewById(R.id.buttonListOfServiceProvided);
        buttonServiceProvided.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(LoginHomeOwner.this, HomeOwnerServiceList.class) );
            }
        } );
    }
}
