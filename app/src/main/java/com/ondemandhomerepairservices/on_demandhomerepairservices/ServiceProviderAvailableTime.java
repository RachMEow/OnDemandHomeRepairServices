package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.internal.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.DayOfWeek;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.SPAvailableTime;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.SPProvidedService;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderAvailableTime extends AppCompatActivity {

    private EditText editTextFrom, editTextTo;

    private Spinner spinnerDay;
    private ListView listViewAvailableTimes;
    private Button btnAddAvailableTime,btnBack;

    private String spId;
    private DayOfWeek day;

    private SPAvailableTime spAvailableTime = new SPAvailableTime();
    List<SPAvailableTime> spAvailableTimes;
    List<String> spAvailableTimeListString;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseAvailableTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_available_time);

        spId = getIntent().getStringExtra("SPID");

        editTextFrom = (EditText) findViewById(R.id.editTextTimeFrom);
        editTextTo = (EditText) findViewById(R.id.editTextTimeTo);

        spinnerDay = (Spinner) findViewById(R.id.spinnerDay);
        listViewAvailableTimes = (ListView) findViewById(R.id.listViewAvailableTime);
        btnAddAvailableTime = (Button) findViewById(R.id.buttonAddAvailableTime);

        ArrayAdapter<DayOfWeek> adapterDay = new ArrayAdapter<DayOfWeek>(this, android.R.layout.simple_spinner_dropdown_item, DayOfWeek.values());
        spinnerDay.setAdapter(adapterDay);

        databaseAvailableTimes = database.getReference("spAvailableTimes");

        //I forgot to write this line
        spAvailableTimes = new ArrayList<>();
        spAvailableTimeListString = new ArrayList<>();

        btnAddAvailableTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validate user input available time
                if(isNotEmptyInputTime(editTextFrom, editTextTo)){

                    if(isValidInputTime(editTextFrom, editTextTo)){
                    int numFrom = Integer.parseInt(String.valueOf(editTextFrom.getText().toString().trim()));
                    int numTo = Integer.parseInt(String.valueOf(editTextTo.getText().toString().trim()));
                    day = (DayOfWeek) spinnerDay.getItemAtPosition(spinnerDay.getSelectedItemPosition());

                    String id = databaseAvailableTimes.push().getKey();
                    spAvailableTime = new SPAvailableTime(id, spId, day, numFrom, numTo);

                    databaseAvailableTimes.child(id).setValue(spAvailableTime);

                    editTextFrom.setText("");
                    editTextTo.setText("");


                    Toast.makeText(getApplicationContext(), "New available time was added", Toast.LENGTH_SHORT).show();

                    }

                }
//                else{
//                    Toast.makeText(getApplicationContext(), "Unable to add new available time", Toast.LENGTH_SHORT).show();
//                }

            }
        });

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        //long click to delete
        listViewAvailableTimes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d( "jjj", "kkk" )
                final SPAvailableTime selectedTime = spAvailableTimes.get(i);

                AlertDialog.Builder yesorno = new AlertDialog.Builder(ServiceProviderAvailableTime.this);
                yesorno.setMessage( "Are you sure to delete this availability from your profile?" )
                        .setCancelable( false )
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteAvailableTIme(selectedTime.getId());
                                Toast.makeText(getApplicationContext(), "Availability was deleted", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        })

                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }) ;

                final AlertDialog b = yesorno.create();
                b.show();

                return true;
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        //get data that belongs to this SP
        Query queryRef = databaseAvailableTimes.orderByChild("spId").equalTo(spId);

        //retrieve data from database: "spAvailableTime"
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                spAvailableTimes.clear();

                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    SPAvailableTime spATime = postSnapShot.getValue(SPAvailableTime.class);
                    spAvailableTimes.add(spATime);
                }


                spAvailableTimeListString.clear();

                for(SPAvailableTime spAT : spAvailableTimes){
                    String s = spAT.toString();
                    spAvailableTimeListString.add(s);
                }

                ArrayAdapter<String> spAvailableTimesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, spAvailableTimeListString);
                listViewAvailableTimes.setAdapter(spAvailableTimesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public boolean isNotEmptyInputTime(EditText editTextFrom, EditText editTextTo){

        String userInputFrom = editTextFrom.getText().toString().trim();
        String userInputTo = editTextTo.getText().toString().trim();

        if(TextUtils.isEmpty(userInputFrom)){
            Toast.makeText(this,"Please enter time FROM", Toast.LENGTH_SHORT).show();
            return false;
        }else if(TextUtils.isEmpty(userInputTo)){
            Toast.makeText(this,"Please enter time TO", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

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

    // delete from spAvailableTime database
    public boolean deleteAvailableTIme(String id){

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("spAvailableTimes").child(id);
        dR.removeValue();
        Toast.makeText(getApplicationContext(),"Availability was deleted from your file", Toast.LENGTH_SHORT).show();
        return true;
    }
}
