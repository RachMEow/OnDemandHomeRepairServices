package com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ondemandhomerepairservices.on_demandhomerepairservices.R;

public class HomeOwnerRatingService extends AppCompatActivity {

    Button btnBack;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_owner_rating_service );

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

    }



}
