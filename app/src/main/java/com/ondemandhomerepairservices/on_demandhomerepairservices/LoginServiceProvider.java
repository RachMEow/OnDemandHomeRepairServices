package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginServiceProvider extends AppCompatActivity {

    Button buttonLogout, btnServicesProvided, btnAvailableTime, btnAddNewService;
    TextView firstName;
//    String spId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_service_provider);

        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        firstName = (TextView) findViewById(R.id.textViewFirstName);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        firstName.setText(getIntent().getStringExtra("USERNAME"));
//        spId = getIntent().getStringExtra("SPID");

        btnServicesProvided = (Button) findViewById(R.id.buttonServicesProvided);
        btnServicesProvided.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent (LoginServiceProvider.this, ServiceProviderServiceProvided.class) );
            }
        } );

        btnAvailableTime = (Button) findViewById(R.id.buttonAvailableTime);
        btnAvailableTime.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent (LoginServiceProvider.this, ServiceProviderAvailableTime.class) );
            }
        } );
        btnAddNewService = (Button) findViewById(R.id.buttonAddNewService);
        btnAddNewService.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent (LoginServiceProvider.this, ServiceProviderAddNewService.class) );
            }
        } );





    }
}
