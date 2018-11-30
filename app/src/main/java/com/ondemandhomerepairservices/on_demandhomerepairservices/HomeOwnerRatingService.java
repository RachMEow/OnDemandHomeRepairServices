package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ondemandhomerepairservices.on_demandhomerepairservices.R;
import com.ondemandhomerepairservices.on_demandhomerepairservices.homeOwner.Rating;

import java.util.ArrayList;
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

        ArrayAdapter<Integer> adapterRating = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, items);
        spinnerRating.setAdapter(adapterRating);

//        ArrayAdapter<CharSequence> adapterRating = ArrayAdapter.createFromResource( this,R.array.numbers, android.R.layout.simple_spinner_item );
//        adapterRating.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

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

                String id = databaseRatings.push().getKey();
                rating = new Rating(id, spProvidedService_id, ho_id, rate, comment);
                databaseRatings.child(id).setValue(rating);

//                editTextComment.setText("");

                Toast.makeText(getApplicationContext(),"Rating saved",Toast.LENGTH_SHORT).show();
                finish();

            }


        });

    }



}
