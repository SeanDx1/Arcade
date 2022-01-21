package com.example.seanactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SevenBoomMainActivity extends AppCompatActivity {

    Button btnPress;
    Integer count = 0;
    TextView counter;
    ImageView boomPng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_boom_main);
        getSupportActionBar().setTitle("Seven Boom");
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#ff00ca"));
        getSupportActionBar().setBackgroundDrawable(cd);

        btnPress = findViewById(R.id.btnPress);
        counter = findViewById(R.id.counter);
        counter.setText("Press Count = " + count);

        final MediaPlayer boomSound = MediaPlayer.create(this,R.raw.boom);
        boomPng = (ImageView) findViewById(R.id.boomPng);
        boomPng.setVisibility(View.INVISIBLE);
        btnPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(count%7==0){
                    Toast.makeText(SevenBoomMainActivity.this,"Boom!",Toast.LENGTH_SHORT).show();
                    boomSound.start();
                    boomPng.setVisibility(View.VISIBLE);
                }else if(count.toString().contains("7")){
                    Toast.makeText(SevenBoomMainActivity.this,"Boom!",Toast.LENGTH_SHORT).show();
                    boomSound.start();
                    boomPng.setVisibility(View.VISIBLE);
                } else{boomPng.setVisibility(View.INVISIBLE);}
                counter.setText("Press Count = " + count);
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
        else if (id == R.id.menu_profile){
            Intent intent = new Intent(SevenBoomMainActivity.this,userProfileActivity.class);
            startActivity(intent);
            finish();
        }
//        else if (id == R.id.menu_settings){
//            Intent intent = new Intent(HomeActivity.this,SettingsActivity.class);
//            startActivity(intent);
//            finish();
        else if (id == R.id.menu_log_out){
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(SevenBoomMainActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SevenBoomMainActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}