package com.example.mluth.suapin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mluth.suapin.Adapter.ItemDonation;
import com.example.mluth.suapin._sliders.FragmentSlider;
import com.example.mluth.suapin._sliders.SliderIndicator;
import com.example.mluth.suapin._sliders.SliderPagerAdapter;
import com.example.mluth.suapin._sliders.SliderView;
import com.example.mluth.suapin.model.Donation;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    public static final String GOOGLE_ACCOUNT = "google_account";
    private GoogleSignInClient googleSignInClient;


    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    private BottomNavigationView bottomNavigationView;

    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Donation> daftarDonation;

    String emailSave,nameSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        sliderView = (SliderView) findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) findViewById(R.id.pagesContainer);
        setupSlider();

        Bundle extras = getIntent().getExtras();

        Intent intent = getIntent();

        if (intent.hasExtra("emailSave")) {
            emailSave = extras.getString("emailSave");
            nameSave = extras.getString("nameSave");
        }

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home: {
                //do somthing
                break;
            }
            case R.id.dashboard: {
                //do somthing
                Intent intent=new Intent(HomeActivity.this, DonationActivity.class);
                intent.putExtra("emailSave", emailSave);
                intent.putExtra("nameSave", nameSave);
                startActivity(intent);
                break;
            }
            case R.id.profile: {
                //do somthing
                Intent intent=new Intent(HomeActivity.this,ProfileActivity.class);
                intent.putExtra("emailSave", emailSave);
                intent.putExtra("nameSave", nameSave);
                startActivity(intent);
                break;
            }
                }

                return true;
            }
        });

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        /**
         * Inisialisasi dan mengambil Firebase Database Reference
         */

        String c="";
        String [] childname = nameSave.split(" ");
        for (int counter = 0; counter < childname.length; counter++){
            c = c+childname[counter];
        }
        database = FirebaseDatabase.getInstance().getReference();
        database.child(c).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                /**
                 * Saat ada data baru, masukkan datanya ke ArrayList
                 */
                daftarDonation = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                    Donation donation = noteDataSnapshot.getValue(Donation.class);
                    donation.setKey(noteDataSnapshot.getKey());


                    daftarDonation.add(donation);
                }

                /**
                 * Inisialisasi adapter dan data barang dalam bentuk ArrayList
                 * dan mengeset Adapter ke dalam RecyclerView
                 */
                adapter = new ItemDonation(daftarDonation, HomeActivity.this);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                /**
                 * Kode ini akan dipanggil ketika ada error dan
                 * pengambilan data gagal dan memprint error nya
                 * ke LogCat
                 */
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://i.imgur.com/nxTdWky.jpg"));
        fragments.add(FragmentSlider.newInstance("https://i.imgur.com/n5khX7F.jpg"));
        fragments.add(FragmentSlider.newInstance("https://i.imgur.com/z9aVIzh.jpg"));
        fragments.add(FragmentSlider.newInstance("https://i.imgur.com/Njr9rcz.jpg"));

        mAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(this, mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

    public void donation(View view) {
        Intent intent = new Intent(HomeActivity.this,DonationActivity.class);
        startActivity(intent);
    }

    public void about(View view) {
        Intent intent = new Intent(HomeActivity.this,AboutActivity.class);
        startActivity(intent);

    }



//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//
//            case R.id.home: {
//                //do somthing
//                break;
//            }
//            case R.id.dashboard: {
//                //do somthing
//                break;
//            }
//            case R.id.notifications: {
//                //do somthing
//                break;
//            }
//            case R.id.profile: {
//                //do somthing
//                break;
//            }
//            case R.id.logout: {
//
//                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                        .requestEmail()
//                        .build();
//
//                googleSignInClient = GoogleSignIn.getClient(this, gso);
//
//                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        //On Succesfull signout we navigate the user back to LoginActivity
//                        Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                    }
//                });
//
//                break;
//            }
//        }
//
//        BottomNavigationView drawer = (BottomNavigationView) findViewById(R.id.bottom_navigation);
//        return true;
//    }

    public void logout(View v){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

                googleSignInClient = GoogleSignIn.getClient(this, gso);

                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //On Succesfull signout we navigate the user back to LoginActivity
                        Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}

