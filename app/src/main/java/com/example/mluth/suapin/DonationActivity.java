package com.example.mluth.suapin;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mluth.suapin.model.Donation;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonationActivity extends AppCompatActivity {

    TextView txtPickLocation,locationSet,lat,longt;
    ImageView pickLocation;
    EditText edTxtName, edTxtFoodName,edTxTPortion;

    private BottomNavigationView bottomNavigationView;

    private DatabaseReference database;

    String emailSave,nameSave;

    public static final String GOOGLE_ACCOUNT = "google_account";
    private GoogleSignInClient googleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        initComoponents();

        Bundle extras = getIntent().getExtras();

        Intent intent = getIntent();

        if (intent.hasExtra("emailSave")) {
            emailSave = extras.getString("emailSave");
            nameSave = extras.getString("nameSave");
        }

        if (intent.hasExtra("location")) {
            String location = extras.getString("location");
            String latitude = extras.getString("latitude");
            String longitude = extras.getString("longitude");
            locationSet.setText(location);
            lat.setText(latitude);
            longt.setText(longitude);
        }

        database = FirebaseDatabase.getInstance().getReference();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home: {
                        //do somthing
                        Intent intent=new Intent(DonationActivity.this,HomeActivity.class);
                        intent.putExtra("emailSave", emailSave);
                        intent.putExtra("nameSave", nameSave);
                        startActivity(intent);
                        break;
                    }
                    case R.id.dashboard: {
                        //do somthing
                        break;
                    }
                    case R.id.profile: {
                        //do somthing
                        Intent intent=new Intent(DonationActivity.this,ProfileActivity.class);
                        intent.putExtra("emailSave", emailSave);
                        intent.putExtra("nameSave", nameSave);
                        startActivity(intent);
                        break;
                    }
                }

                return true;
            }
        });
    }

    public void lokasi (View view) {
        Intent intent = new Intent(DonationActivity.this,LocationActivity.class);
        intent.putExtra("emailSave", emailSave);
        intent.putExtra("nameSave", nameSave);
        startActivity(intent);
    }

    public void donate(View view){
        if(inputValidation()){
            donating(new Donation(
                    locationSet.getText().toString(),
                    edTxtName.getText().toString(),
                    edTxtFoodName.getText().toString(),
                    lat.getText().toString(),
                    longt.getText().toString(),
                    Integer.parseInt(edTxTPortion.getText().toString())));
        }else{
            Snackbar.make(view, "Data Invalid", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    public void initComoponents(){

        txtPickLocation = findViewById(R.id.txtPickLocation);
        locationSet = findViewById(R.id.locationSet);
        lat = findViewById(R.id.lat);
        longt = findViewById(R.id.longt);

        pickLocation = findViewById(R.id.pickLocation);

        edTxtName = findViewById(R.id.edTxtName);
        edTxtFoodName = findViewById(R.id.edTxtFoodName);
        edTxTPortion = findViewById(R.id.edTxTPortion);

    }

    public boolean inputValidation(){
        boolean v = false;
        if(!locationSet.getText().toString().equalsIgnoreCase("Your Location")
        && !edTxtName.getText().toString().equalsIgnoreCase("")
        && !edTxtFoodName.getText().toString().equalsIgnoreCase("")
        && !edTxTPortion.getText().toString().equalsIgnoreCase("")){
            v = true;
        }
        return v;
    }
    public void donating(Donation dataDonation){
        String c="";
        String [] childname = nameSave.split(" ");
        for (int counter = 0; counter < childname.length; counter++){
            c = c+childname[counter];
        }
        database.child(c).push().setValue(dataDonation).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(DonationActivity.this, "Donation Success",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DonationActivity.this, HomeActivity.class);
                intent.putExtra("emailSave", emailSave);
                intent.putExtra("nameSave", nameSave);
                startActivity(intent);
                finishAffinity();
            }
        });
    }

}
