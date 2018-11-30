package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ondemandhomerepairservices.on_demandhomerepairservices.R;
import com.ondemandhomerepairservices.on_demandhomerepairservices.admin.Service;
import com.ondemandhomerepairservices.on_demandhomerepairservices.homeOwner.Rating;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class HomeOwnerRatingService extends AppCompatActivity {

    private Spinner spinnerRating;
    private EditText editTextComment;
    private String ho_id, spProvidedService_id;

    Button btnBack, btnRate;

    private Rating rating = new Rating();
    List<Rating> ratings;
    List<String> ratingListString;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseRatings;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_owner_rating_service );

        ho_id= getIntent().getStringExtra("HOID");
        spProvidedService_id = getIntent().getStringExtra("spProvidedService_id");

        editTextComment = (EditText) findViewById(R.id.editTextComment);

        spinnerRating = (Spinner) findViewById(R.id.spinner);

        Integer[] items = new Integer[]{1,2,3,4,5};

//        ArrayAdapter<CharSequence> adapterRating = ArrayAdapter.createFromResource( this,R.array.numbers, android.R.layout.simple_spinner_item );
        ArrayAdapter<Integer> adapterRating = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, items);
//        adapterRating.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinnerRating.setAdapter(adapterRating);

        databaseRatings = database.getReference("ratings");
        ratings = new ArrayList<>();
        ratingListString = new ArrayList<>();

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        btnRate = (Button) findViewById(R.id.buttonRate);
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int rate = (int) spinnerRating.getItemAtPosition(spinnerRating.getSelectedItemPosition());
//                Toast.makeText(getApplicationContext(), ""+rate, Toast.LENGTH_SHORT).show();

                String comment = editTextComment.getText().toString().trim();

                if(isNotExistInRating(spProvidedService_id)){
                    String id = databaseRatings.push().getKey();
                    rating = new Rating(id, spProvidedService_id, ho_id, rate, comment);
                    databaseRatings.child(id).setValue(rating);

                }


//                editTextComment.setText("");

                Toast.makeText(getApplicationContext(),"Rating saved",Toast.LENGTH_SHORT).show();
                finish();

            }


        });

    }

    public boolean isNotExistInRating(final String selectedServiceId){
        //get data that belongs to this SP

        Query queryRef = databaseRatings.orderByChild("spProvidedService_id").equalTo(spProvidedService_id);
        // Retrieve services added by ADMIN from database
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                spProvidedServices.clear();
                Hashtable<String, Integer> check = new Hashtable<String, Integer>();
                for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    Rating temp = postSnapShot.getValue(Rating.class);
                    String serviceName = temp.getSpProvidedService_id();
                    Log.d("check id:", serviceName);
//                    String id = temp.get_id();
                    if(check.containsKey( serviceName )) {
                        Toast.makeText(getApplicationContext(), "Service already rated", Toast.LENGTH_LONG).show();
                        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("ratings").child(postSnapShot.getKey());
                        Log.i("removing: ", postSnapShot.getKey());
                        dR.removeValue();
                        return;
                    } else {
                        check.put( serviceName, 1 );
                    }
                }
//                Toast.makeText(getApplicationContext(), "Service added to your profile", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return true;
    }



}
