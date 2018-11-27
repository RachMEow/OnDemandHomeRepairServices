package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.SPProvidedService;

import java.util.ArrayList;
import java.util.List;

public class HomeOwnerServiceList extends AppCompatActivity {
    Button btnBack;
    ListView listViewServiceProvided;

    // get the value of intent.putExtra
    String searchContent = "serviceName";
    String userInputServiceName = "Cleaning";

    private SPProvidedService spProvidedService = new SPProvidedService();
    List<SPProvidedService> spProvidedServices;
    List<String> spProvidedServicesListString;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseProvidedServices = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_owner_service_list );

        listViewServiceProvided = (ListView)findViewById(R.id.listViewServiceList);
        databaseProvidedServices = FirebaseDatabase.getInstance().getReference("spProvidedServices");
        spProvidedServices = new ArrayList<>();
        spProvidedServicesListString = new ArrayList<>();

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

    }

    @Override
    protected void onStart(){
        super.onStart();

        Query queryRef = databaseProvidedServices.orderByChild("_serviceName").equalTo(userInputServiceName);

        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                spProvidedServices.clear();

                for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){

                    SPProvidedService spProvidedService = postSnapShot.getValue(SPProvidedService.class);
                    spProvidedServices.add(spProvidedService);

                }

                spProvidedServicesListString.clear();

                for(SPProvidedService spProvidedService : spProvidedServices){
                    String s = spProvidedService.toString();
                    spProvidedServicesListString.add(s);
                }

                ArrayAdapter<String> servicesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, spProvidedServicesListString);
                listViewServiceProvided.setAdapter(servicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
