package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import android.widget.EditText;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.SPAvailableTime;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import android.widget.Toast;

public class HomeOwnerSearchServiceProvider extends AppCompatActivity {

    Button btnBack,btnSearch1,btnSearch2,btnSearch3;

    private DayOfWeek day;
    private Spinner spinnerDay,spinnerRating;
    private EditText editTextServiceName;
    private EditText editTextFrom, editTextTo;
    private String spId,dayString;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseAvailableTimes;

    private String ho_id;
    private String searchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_owner_search_service_provider );

        //Service name case
        ho_id = getIntent().getStringExtra("HOID");

        editTextFrom = (EditText) findViewById(R.id.editTextTimeFrom);
        editTextTo = (EditText) findViewById(R.id.editTextTimeTo);

        editTextServiceName = (EditText) findViewById(R.id.editTextServiceName);


        //Time case
        spinnerDay = (Spinner) findViewById(R.id.spinnerDay);
        ArrayAdapter<DayOfWeek> adapterDay = new ArrayAdapter<DayOfWeek>(this, android.R.layout.simple_spinner_dropdown_item, DayOfWeek.values());
        spinnerDay.setAdapter(adapterDay);

//        Log.i("DayOfWeekHOSSP", day.toString());
//        dayString = day.toString();

        spinnerRating = (Spinner) findViewById(R.id.spinnerRating);

        Integer[] items = new Integer[]{1,2,3,4,5};

        ArrayAdapter<Integer> adapterRating = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerRating.setAdapter(adapterRating);
//        ArrayAdapter<CharSequence> adapterRating = ArrayAdapter.createFromResource( this,R.array.numbers, android.R.layout.simple_spinner_item );
//        adapterRating.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinnerRating.setAdapter(adapterRating);

        editTextFrom = (EditText) findViewById(R.id.editTextTimeFrom);
        editTextTo = (EditText) findViewById(R.id.editTextTimeTo);

        spId = getIntent().getStringExtra("SPID");
        databaseAvailableTimes = database.getReference("spAvailableTimes");

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        //If search service name
        btnSearch1 = (Button) findViewById(R.id.buttonSearch1);
        btnSearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String serviceName = editTextServiceName.getText().toString().trim();
                searchType = "serviceName";

                if(isServiceNameValidate(editTextServiceName)){

                    Intent intent;
                    intent = new Intent(HomeOwnerSearchServiceProvider.this, HomeOwnerServiceList.class);
                    intent.putExtra("searchType", searchType);
                    intent.putExtra("serviceName", serviceName);
                    intent.putExtra("HOID", ho_id);
                    startActivity(intent);
                }


            }
        });

        btnSearch2 = (Button)findViewById(R.id.buttonSearch2);
        btnSearch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String timeBegin = editTextFrom.getText().toString().trim();
                String timeEnd = editTextTo.getText().toString().trim();

                day = (DayOfWeek) spinnerDay.getItemAtPosition(spinnerDay.getSelectedItemPosition());

                if(isNotEmptyInputTime(editTextFrom,editTextTo)){
                    if(isValidInputTime(editTextFrom, editTextTo)){
                        //search
                        searchType = "time";

                        Intent intent;
                        intent = new Intent(HomeOwnerSearchServiceProvider.this, HomeOwnerServiceList.class);
                        intent.putExtra("searchType",searchType);
                        intent.putExtra("timeBegin",timeBegin);
                        intent.putExtra("timeEnd",timeEnd);
                        intent.putExtra("Day",day);
                       // intent.putExtra("SPID",spId);
                        startActivity(intent);

                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Information cannot be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSearch3 = (Button)findViewById(R.id.buttonSearch3);
        btnSearch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

//                int rating  = (int) spinnerRating.getItemAtPosition(spinnerRating.getSelectedItemPosition());
//                searchType = "rating";
//
//                if(true) {
//
//                    Intent intent;
//                    intent = new Intent(HomeOwnerSearchServiceProvider.this, HomeOwnerServiceList.class);
//                    intent.putExtra("searchType", searchType);
//                    intent.putExtra("rating", rating);
//                    startActivity(intent);
//                }



            }
        });
    }


    //service name validate
    public boolean isServiceNameValidate(EditText editTextServiceName){

        String stringEditTextServiceName = editTextServiceName.getText().toString().trim();

        Pattern p2 = Pattern.compile("[^a-z0-9_ ]", Pattern.CASE_INSENSITIVE );

        if(TextUtils.isEmpty(stringEditTextServiceName)) {
            Toast.makeText(this, "Please enter a service name", Toast.LENGTH_SHORT).show();
            return false;
        }else if(p2.matcher(stringEditTextServiceName).find()){
            Toast.makeText(getApplicationContext(), "Service name can only contain letter, number and underscore", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;



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

            if((numFrom < 0) || (numTo < 0) || (numFrom > 24) || (numTo > 24)){
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
