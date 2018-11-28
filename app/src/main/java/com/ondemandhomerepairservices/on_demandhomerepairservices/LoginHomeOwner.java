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

    String ho_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_home_owner );

        ho_id = getIntent().getStringExtra("HOID");

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
                Intent intent;
                intent = new Intent (LoginHomeOwner.this, HomeOwnerBookedServices.class);
                intent.putExtra("HOID", ho_id);
                startActivity(intent);
            }
        } );

        buttonServiceProvided = (Button) findViewById(R.id.buttonListOfServiceProvided);
        buttonServiceProvided.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent (LoginHomeOwner.this, HomeOwnerSearchServiceProvider.class);
                intent.putExtra("HOID", ho_id);
                startActivity(intent);
            }
        } );
    }
}
