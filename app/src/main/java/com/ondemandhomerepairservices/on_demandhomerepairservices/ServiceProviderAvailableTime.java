package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.AvailableTime;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.DayOfWeek;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderAvailableTime extends AppCompatActivity {

    Spinner spinnerDay, spinnerTime;
    ListView listViewAvailableTimes;
    Button btnAddAvailableTime;

    AvailableTime availableTime = new AvailableTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_available_time);

        spinnerDay = (Spinner) findViewById(R.id.spinnerDay);
        spinnerTime = (Spinner) findViewById(R.id.spinnerTime);
        listViewAvailableTimes = (ListView) findViewById(R.id.listViewAvailableTime);
        btnAddAvailableTime = (Button) findViewById(R.id.buttonAddAvailableTime);

        ArrayAdapter<DayOfWeek> adapterDay = new ArrayAdapter<DayOfWeek>(this, android.R.layout.simple_spinner_dropdown_item, DayOfWeek.values());
        spinnerDay.setAdapter(adapterDay);

        ArrayAdapter<TimeSlot> adapterTime = new ArrayAdapter<TimeSlot>(this, android.R.layout.simple_spinner_dropdown_item, TimeSlot.values());
        spinnerTime.setAdapter(adapterTime);

        btnAddAvailableTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get selected value from spinner day and time
                DayOfWeek day = (DayOfWeek)spinnerDay.getItemAtPosition(spinnerDay.getSelectedItemPosition());
                TimeSlot time = (TimeSlot) spinnerTime.getItemAtPosition(spinnerTime.getSelectedItemPosition());

                Toast.makeText(ServiceProviderAvailableTime.this,"time is " + time, Toast.LENGTH_SHORT).show();

                availableTime = new AvailableTime(day, time);

                String[] availableTimes = {availableTime.toString()};

//                ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, availableTimes);
//                listViewAvailableTimes.setAdapter(adapterList);
            }
        });

    }

}
