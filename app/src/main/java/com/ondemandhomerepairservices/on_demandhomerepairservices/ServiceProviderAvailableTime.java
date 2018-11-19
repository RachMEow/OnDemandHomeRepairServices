package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.DayOfWeek;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderAvailableTime extends AppCompatActivity {

    Spinner spinnerDay, spinnerTime;
    ListView listViewAvailableTimes;
    Button btnAddAvailableTime,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_available_time);

        spinnerDay = (Spinner) findViewById(R.id.spinnerDay);
        listViewAvailableTimes = (ListView) findViewById(R.id.listViewAvailableTime);
        btnAddAvailableTime = (Button) findViewById(R.id.buttonAddAvailableTime);

        ArrayAdapter<DayOfWeek> adapterDay = new ArrayAdapter<DayOfWeek>(this, android.R.layout.simple_spinner_dropdown_item, DayOfWeek.values());
        spinnerDay.setAdapter(adapterDay);

        btnAddAvailableTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Todo: button add available time
            }
        });

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(ServiceProviderAvailableTime.this, LoginServiceProvider.class) );
            }
        } );

    }

}
