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
    private String sp_id;
    private String ho_id;

    Button btnBack, btnRate;

    private Rating rating = new Rating();
    List<Rating> ratings;
    List<String> ratingListString;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseRatings;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_owner_rating_service );

        sp_id= getIntent().getStringExtra("SP_ID");
        ho_id= getIntent().getStringExtra("HO_ID");




        editTextComment = (EditText) findViewById(R.id.editTextComment);

        spinnerRating = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapterRating = ArrayAdapter.createFromResource( this,R.array.numbers, android.R.layout.simple_spinner_item );
        adapterRating.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinnerRating.setAdapter(adapterRating);

        databaseRatings = database.getReference("ratings");

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

                ratings = new ArrayList<>();
                ratingListString = new ArrayList<>();

                String comment = String.valueOf(editTextComment.getText().toString().trim());

                //needs fixing
                int rate = 0; /*= (Integer) spinnerRating.getAutofillValue();*/

                String id = databaseRatings.push().getKey();

                rating = new Rating(id,sp_id,ho_id,rate, comment);

                databaseRatings.child(id).setValue(rating);

                editTextComment.setText("");



                spinnerRating = (Spinner) spinnerRating.getItemAtPosition(spinnerRating.getSelectedItemPosition());
                Toast.makeText(getApplicationContext(),"rating saved",Toast.LENGTH_SHORT).show();

            }


        });

    }



}
