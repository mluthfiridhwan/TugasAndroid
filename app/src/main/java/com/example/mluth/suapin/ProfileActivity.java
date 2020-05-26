package com.example.mluth.suapin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    public static final String GOOGLE_ACCOUNT = "google_account";
    private GoogleSignInClient googleSignInClient;

    private TextView profileName, profileEmail;
    private ImageView profileImage;
    private Button signOut;

    private String emailSave,nameSave;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profile_text);
        profileEmail = findViewById(R.id.profile_email);
//        profileImage = findViewById(R.id.profile_image);

        setDataOnView();


        signOut=findViewById(R.id.sign_out);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          /*
          Sign-out is initiated by simply calling the googleSignInClient.signOut API. We add a
          listener which will be invoked once the sign out is the successful
           */
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();
                googleSignInClient = GoogleSignIn.getClient(ProfileActivity.this, gso);
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //On Succesfull signout we navigate the user back to LoginActivity
                        Intent intent=new Intent(ProfileActivity.this,LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finishAffinity();
                    }
                });
            }
        });
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home: {
                        //do somthing
                        Intent intent=new Intent(ProfileActivity.this,HomeActivity.class);
                        intent.putExtra("emailSave", emailSave);
                        intent.putExtra("nameSave", nameSave);
                        startActivity(intent);
                        break;
                    }
                    case R.id.dashboard: {
                        //do somthing
                        Intent intent=new Intent(ProfileActivity.this,DonationActivity.class);
                        intent.putExtra("emailSave", emailSave);
                        intent.putExtra("nameSave", nameSave);
                        startActivity(intent);
                        break;
                    }
                    case R.id.profile: {
                        //do somthing
                        break;
                    }
                }

                return true;
            }
        });
    }
    private void setDataOnView() {
        GoogleSignInAccount googleSignInAccount = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);
//        Picasso.get().load(googleSignInAccount.getPhotoUrl()).centerInside().fit().into(profileImage);
        Bundle extras = getIntent().getExtras();

        Intent intent = getIntent();

        if (intent.hasExtra("emailSave")) {
            emailSave = extras.getString("emailSave");
            nameSave = extras.getString("nameSave");
            profileName.setText(nameSave);
            profileEmail.setText(emailSave);
        } else {
            // Do something else
            profileName.setText(googleSignInAccount.getDisplayName());
            nameSave = googleSignInAccount.getDisplayName();
            emailSave = googleSignInAccount.getEmail();
            profileEmail.setText(googleSignInAccount.getEmail());

        }
//        Toast.makeText(ProfileActivity.this, String.valueOf(googleSignInAccount.getPhotoUrl()),Toast.LENGTH_LONG).show();
    }

    public void save(View view) {
    }

    public void cancel(View view) {
    }
}
