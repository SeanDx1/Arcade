package com.example.seanactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class HomeActivity extends AppCompatActivity {
    private TextView textViewWelcome;
    private ImageButton imageButtonGuessingNum;
    private ImageButton imageButtonSevenBoom;
    private ImageButton imageButtonTicTacToe;
    private FirebaseAuth authProfile;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Arcade");
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#ff00ca"));
        getSupportActionBar().setBackgroundDrawable(cd);

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();


        imageButtonGuessingNum = findViewById(R.id.GuessNumBtnHome);
        imageButtonSevenBoom = findViewById(R.id.sevenBoomBtn);
        imageButtonTicTacToe = findViewById(R.id.TicTacToeBtn);
        textViewWelcome = findViewById(R.id.userWelcome);


        imageButtonGuessingNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this,GuessingNumberActivity.class);
                startActivity(intent);

            }
        });

        imageButtonSevenBoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this,SevenBoomMainActivity.class);
                startActivity(intent);

            }
        });

        imageButtonTicTacToe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this,TicTacToeActivity.class);
                startActivity(intent);

            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(firebaseUser.getUid()).child("full_name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name = snapshot.getValue(String.class);
                textViewWelcome.setText("Welcome "+ name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    //--- Menu ToolBar---\\
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.common_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_refresh || id == R.id.menu_home){
            startActivity(getIntent());
            finish();
            overridePendingTransition(0,0);
        }
        //else if (id == R.id.menu_profile){
//            Intent intent = new Intent(HomeActivity.this,ProfileActivity.class);
//            startActivity(intent);
//            finish();
//        }else if (id == R.id.menu_settings){
//            Intent intent = new Intent(HomeActivity.this,SettingsActivity.class);
//            startActivity(intent);
//            finish();
        else if (id == R.id.menu_log_out){
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(HomeActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}