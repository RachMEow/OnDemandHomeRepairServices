package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.DayOfWeek;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.SPAvailableTime;

import java.util.ArrayList;
import java.util.List;

public class HomeOwnerSearchServiceProvider extends AppCompatActivity {

    Button btnBack,btnSearch1,btnSearch2,btnSearch3;
    private DayOfWeek day;
    private Spinner spinnerDay,spinnerRating;
    private EditText editTextFrom, editTextTo;
    private AbsListView.RecyclerListener ResultList;
    private String timeId;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseAvailableTimes;


    private SPAvailableTime spAvailableTime = new SPAvailableTime();
    List<SPAvailableTime> spAvailableTimes;
    List<String> spAvailableTimeListString;


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


        //timeDatabase
        timeId = getIntent().getStringExtra("TIMEID");
        databaseAvailableTimes = database.getReference("spAvailableTimes");
        spAvailableTimes = new ArrayList<>();
        spAvailableTimeListString = new ArrayList<>();



        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        btnSearch2 = (Button)findViewById(R.id.buttonSearch2);
        btnSearch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNotEmptyInputTime(editTextFrom,editTextTo)){
                    if(isValidInputTime(editTextFrom, editTextTo)){
                        //search

                        //FirbaseSearchByTime();

                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Information cannot be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
/*
    protected void FirbaseSearchByTime(){
       // String timeBegin = editTextFrom.getText().toString().trim();
       // String timeEnd = editTextTo.getText().toString().trim();
       // String weekDay = spinnerDay.getSelectedItem().toString().trim();

        Query searchQuery = databaseAvailableTimes.orderByChild("timeId").endAt(timeId);
        searchQuery.addChildEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                spAvailableTimes.clear();

                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                    SPAvailableTime spATime = postSnapShot.getValue(SPAvailableTime.class);
                    spAvailableTimes.add(spATime);
                }

                spAvailableTimeListString.clear();

                for(SPAvailableTime spAvailableTime1:spAvailableTimes){
                    String time = spAvailableTime1.toString();
                    spAvailableTimeListString.add(time);
                }



                ArrayAdapter<String> adapter = new ArrayAdapter<String> (getApplicationContext(),,spAvailableTimeListString);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }*/
    public boolean isValidInputTime(EditText editTextFrom, EditText editTextTo){

        String userInputFrom = editTextFrom.getText().toString().trim();
        String userInputTo = editTextTo.getText().toString().trim();
        int numFrom = 0;
        int numTo = 0;

        if(isNotEmptyInputTime(editTextFrom, editTextTo)){

            try{
                numFrom = Integer.parseInt(String.valueOf(userInputFrom));
                numTo = Integer.parseInt(String.valueOf(userInputTo));
            }catch (NumberFormatException ignore){
                Toast.makeText(this, "Enter a valid number!", Toast.LENGTH_SHORT).show();
                return false;
            }

            if((numFrom < 0) || (numTo < 0) || (numFrom > 24) || (numFrom > 24)){
                Toast.makeText(this,"Enter a number between 0 and 24", Toast.LENGTH_SHORT).show();
                return false;
            }else if((numFrom >= numTo)){
                Toast.makeText(this,"FROM is larger than or equal to TO", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    public boolean isNotEmptyInputTime(EditText editTextFrom, EditText editTextTo) {

        String userInputFrom = editTextFrom.getText().toString().trim();
        String userInputTo = editTextTo.getText().toString().trim();

        if (TextUtils.isEmpty(userInputFrom)) {
            Toast.makeText(this, "Please enter time from", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(userInputTo)) {
            Toast.makeText(this, "Please enter time to", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
