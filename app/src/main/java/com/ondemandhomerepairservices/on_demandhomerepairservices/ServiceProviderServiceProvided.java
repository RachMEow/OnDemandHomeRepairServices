package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ondemandhomerepairservices.on_demandhomerepairservices.admin.Service;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.SPProvidedService;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderServiceProvided extends AppCompatActivity{

    Button btnBack;
    ListView listViewServiceProvided;

    String spId;

    private SPProvidedService spProvidedService = new SPProvidedService();
    List<SPProvidedService> spProvidedServices;
    List<String> spProvidedServicesListString;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseProvidedServices = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_service_provided);

        listViewServiceProvided = (ListView)findViewById(R.id.listViewServiceProvidedList);

        databaseProvidedServices = FirebaseDatabase.getInstance().getReference("spProvidedServices");
        spProvidedServices = new ArrayList<>();
        spProvidedServicesListString = new ArrayList<>();

        spId = getIntent().getStringExtra( "SPID" );

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        //long click to delete
        listViewServiceProvided.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d( "jjj", "kkk" )
                final SPProvidedService selectedService = spProvidedServices.get(i);

                AlertDialog.Builder yesorno = new AlertDialog.Builder(ServiceProviderServiceProvided.this);
                yesorno.setMessage( "Are you sure to delete this service from your profile?" )
                        .setCancelable( false )
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteServiceProvided(selectedService.getSpProvidedService_id());
                                Toast.makeText(getApplicationContext(), "Service deleted", Toast.LENGTH_SHORT).show();
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
        Query queryRef = databaseProvidedServices.orderByChild("spId").equalTo(spId);


        // Retrieve services added by ADMIN from database
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

    // delete from spServiceProvided database
    public boolean deleteServiceProvided(String id){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("spProvidedServices").child(id);
        dR.removeValue();
        Toast.makeText(getApplicationContext(),"Service Deleted from your file", Toast.LENGTH_LONG).show();
        return true;
    }
}
