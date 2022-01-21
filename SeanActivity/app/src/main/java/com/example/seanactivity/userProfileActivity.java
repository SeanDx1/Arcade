package com.example.seanactivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class userProfileActivity extends AppCompatActivity {

    private TextView textViewWelcome,textViewFullName,textViewEmail,textViewDoB,textViewGender,textViewMobile,textViewWallet;
    private ProgressBar progressBar;
    private String fullname;
    private String email;
    private String dob;
    private String gender;
    private String mobile;
    private int wallet;
    private ImageView imageView,imageViewWallet;
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    private Button buttonAddressDetails, buttonProfileEdit;
    private DatabaseReference databaseReference;
    private UserClass ReadUserDetails;
    private ScoreBoard userScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);



        getSupportActionBar().setTitle("Profile");
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#ff00ca"));
        getSupportActionBar().setBackgroundDrawable(cd);


        textViewWelcome = findViewById(R.id.textView_show_welcome);
        textViewFullName = findViewById(R.id.textView_show_full_name);
        textViewEmail = findViewById(R.id.textView_show_email);
        textViewDoB = findViewById(R.id.textView_show_dob);
        textViewGender = findViewById(R.id.textView_show_gender);
        textViewMobile = findViewById(R.id.textView_show_mobile);
        progressBar = findViewById(R.id.progressBar);
        buttonProfileEdit = findViewById(R.id.button_edit_profile);



//        buttonProfileEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ProfileActivity.this,UpdateProfileActivity.class);
//                startActivity(intent);
//            }
//        });

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();

        if(firebaseUser == null){
            Toast.makeText(userProfileActivity.this,"Something went wrong ! User's details are not available",Toast.LENGTH_LONG).show();
        }else{
            progressBar.setVisibility(View.VISIBLE);
            showUserProfile(firebaseUser);
        }



    }
    private void showUserProfile(FirebaseUser firebaseUser){
        String userID= firebaseUser.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(userID).exists()){
                    databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ReadUserDetails = snapshot.getValue(UserClass.class);
                            if(ReadUserDetails != null){
                                fullname= ReadUserDetails.getFull_name();
                                email = authProfile.getCurrentUser().getEmail();
                                dob = ReadUserDetails.getDob();
                                gender = ReadUserDetails.getGender();
                                mobile = ReadUserDetails.getPhone();
                                textViewWelcome.setText("Welcome, " + fullname+ "!");
                                textViewFullName.setText(fullname);
                                textViewEmail.setText(email);
                                textViewDoB.setText(dob);
                                textViewGender.setText(gender);
                                textViewMobile.setText(mobile);
                                progressBar = findViewById(R.id.progressBar);



                            }else{
                                Toast.makeText(userProfileActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            }
                            progressBar.setVisibility(View.GONE);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(userProfileActivity.this,"Something went wrong ! ",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    });


                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(userProfileActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            };
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.common_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_refresh || id == R.id.menu_profile){
            startActivity(getIntent());
            finish();
            overridePendingTransition(0,0);
        }
        else if (id == R.id.menu_home){
            Intent intent = new Intent(userProfileActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
//        else if (id == R.id.menu_settings){
//            Intent intent = new Intent(HomeActivity.this,SettingsActivity.class);
//            startActivity(intent);
//            finish();
        else if (id == R.id.menu_log_out){
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(userProfileActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(userProfileActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
