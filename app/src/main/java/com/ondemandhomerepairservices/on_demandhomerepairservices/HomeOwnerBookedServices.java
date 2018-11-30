package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ondemandhomerepairservices.on_demandhomerepairservices.admin.Service;
import com.ondemandhomerepairservices.on_demandhomerepairservices.homeOwner.HOBookedService;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.SPProvidedService;

import java.util.ArrayList;
import java.util.List;

public class HomeOwnerBookedServices extends AppCompatActivity {
    Button btnBack;
    ListView listViewServicebooked;

    String hoId;

    private HOBookedService hoBookedService = new HOBookedService();
    List<HOBookedService> hoBookedServices;
    List<String> hoBookedServicesListString;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseBookedService = database.getReference("message");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_owner_booked_service );


        listViewServicebooked = (ListView)findViewById(R.id.listViewServiceList);

        databaseBookedService = FirebaseDatabase.getInstance().getReference("hoBookedServices");
        hoBookedServices = new ArrayList<>();
        hoBookedServicesListString = new ArrayList<>();

        hoId = getIntent().getStringExtra( "HOID" );

        listViewServicebooked.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

//            int selectedService = listViewAdminServices.getSelectedItemPosition();

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

//                Toast.makeText(ServiceProviderAddNewService.this,""+selectedService.get_serviceName(), Toast.LENGTH_SHORT).show();
                HOBookedService selectedService = hoBookedServices.get(i);
                final String spProvidedService_id = selectedService.getSpProvidedService_id();

                AlertDialog.Builder yesorno = new AlertDialog.Builder(HomeOwnerBookedServices.this);
                yesorno.setMessage( "Are you sure to rate this service?" )
                        .setCancelable( false )
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent;
                                intent = new Intent(HomeOwnerBookedServices.this, HomeOwnerRatingService.class);
                                intent.putExtra("HOID", hoId);
                                intent.putExtra("spProvidedService_id",spProvidedService_id);
                                startActivity(intent);
//
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


        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }


    protected void onStart(){
        super.onStart();

        //get data that belongs to this HO
        Query queryRef = databaseBookedService.orderByChild("ho_id").equalTo(hoId);


        // Retrieve services added by ADMIN from database
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hoBookedServices.clear();

                for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){

                    HOBookedService hoBookedService = postSnapShot.getValue(HOBookedService.class);
                    hoBookedServices.add(  hoBookedService );

                }

                hoBookedServicesListString.clear();

                for(HOBookedService hoBookedService : hoBookedServices){
                    String s = hoBookedService.get_serviceName() + " provided by " + hoBookedService.getSpCompanyName();
                    hoBookedServicesListString.add(s);
                }

                ArrayAdapter<String> servicesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, hoBookedServicesListString);
                listViewServicebooked.setAdapter(servicesAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
//    no need to delete
//    public boolean deleteServiceProvided(String id){
//        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("BookedService").child(id);
//        dR.removeValue();
//        Toast.makeText(getApplicationContext(),"Service Deleted from your file", Toast.LENGTH_LONG).show();
//        return true;
//    }
}
