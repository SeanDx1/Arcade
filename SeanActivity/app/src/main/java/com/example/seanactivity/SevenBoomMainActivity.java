package com.example.seanactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SevenBoomMainActivity extends AppCompatActivity {

    Button btnPress;
    Integer count = 0;
    TextView counter;

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

        btnPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(count%7==0){
                    Toast.makeText(SevenBoomMainActivity.this,"Boom!",Toast.LENGTH_SHORT).show();
                }else if(count.toString().contains("7")){
                    Toast.makeText(SevenBoomMainActivity.this,"Boom!",Toast.LENGTH_SHORT).show();
                }
                counter.setText("Press Count = " + count);
            }
        });
    }
}