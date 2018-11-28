package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.DayOfWeek;
import android.widget.EditText;

public class HomeOwnerSearchServiceProvider extends AppCompatActivity {

    Button btnBack,btnSearch1,btnSearch2,btnSearch3;
    private DayOfWeek day;
    private Spinner spinnerDay,spinnerRating;
    private EditText editTextFrom, editTextTo;
    EditText editTextServiceName;
    String searchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_owner_search_service_provider );

        editTextFrom = (EditText) findViewById(R.id.editTextTimeFrom);
        editTextTo = (EditText) findViewById(R.id.editTextTimeTo);

        spinnerDay = (Spinner) findViewById(R.id.spinnerDay);
        ArrayAdapter<DayOfWeek> adapterDay = new ArrayAdapter<DayOfWeek>(this, android.R.layout.simple_spinner_dropdown_item, DayOfWeek.values());
        spinnerDay.setAdapter(adapterDay);
        day = (DayOfWeek) spinnerDay.getItemAtPosition(spinnerDay.getSelectedItemPosition());

        spinnerRating = (Spinner) findViewById(R.id.spinnerRating);
        ArrayAdapter<CharSequence> adapterRating = ArrayAdapter.createFromResource( this,R.array.numbers, android.R.layout.simple_spinner_item );
        adapterRating.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinnerRating.setAdapter(adapterRating);



        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        btnSearch1 = (Button) findViewById(R.id.buttonSearch1);
        btnSearch2 = (Button) findViewById(R.id.buttonSearch2);
        btnSearch3 = (Button) findViewById(R.id.buttonSearch3);

        editTextServiceName = (EditText) findViewById(R.id.editTextServiceName);

        btnSearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(HomeOwnerSearchServiceProvider.this, HomeOwnerServiceList.class);
                searchType = "serviceName";
                intent.putExtra("searchType", searchType);
                startActivity(intent);
            }
        });

    }
}
