package com.example.seanactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GuessingNumberActivity extends AppCompatActivity implements View.OnClickListener {

    static int TRIES = 10;
    Button btnGuess,btnReset;
    EditText guessingNumberInput;
    Random r;
    int randomNumber,tries = TRIES,stageNum = 1;
    TextView remainTries,stageNumber;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing_number);
        getSupportActionBar().setTitle("Guess The Number");
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#ff00ca"));
        getSupportActionBar().setBackgroundDrawable(cd);

        // initialize the view from the XML.
        initViews();

        // initialize variables
        initVarbs();

        // initialize UI
        initUI();

        // init Buttons
        initButtons();
    }

    private void initButtons() {
        btnGuess.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    private void initUI() {
        stageNum = prefs.getInt("Level",1);
        resetUpdateGame(stageNum);
    }

    private void initVarbs() {
        prefs = getSharedPreferences("GuessNumber", MODE_PRIVATE);
        r = new Random();
    }

    private void initViews() {
        btnGuess = findViewById(R.id.btnGuess);
        guessingNumberInput = findViewById(R.id.guessingNumberInput);
        remainTries = findViewById(R.id.remainTries);
        btnReset = findViewById(R.id.btnReset);
        stageNumber = findViewById(R.id.stageNumber);
    }

    /**
     * getting stage number, and initialize all the variables compare to it.
     */
    private void resetUpdateGame(int stageNum) {
        int tries = 10;
        switch (stageNum){
            case 1: tries = 10;
                randomNumber = r.nextInt(10);
                break;
            case 2:
                tries = 5;
                randomNumber = r.nextInt(15);
                break;
            case 3:
                tries = 3;
                randomNumber = r.nextInt(25);
                break;
        }
        stageNumber.setText("Level " + stageNum);
        this.tries = tries;

        remainTries.setText("Guessing remain: " + tries);
        btnGuess.setClickable(true);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Level",stageNum);
        editor.apply();
        Toast.makeText(GuessingNumberActivity.this,"Random num: " +randomNumber,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnGuess:
                guessingNumberAction();
                break;
            case R.id.btnReset:
                resetUpdateGame(1);
                break;
        }
    }

    private void guessingNumberAction() {
        if(tries == 0){
            Toast.makeText(GuessingNumberActivity.this,"Game Over!",Toast.LENGTH_SHORT).show();
            btnGuess.setClickable(false);
            return;
        }
        tries--;
        remainTries.setText("Guessing remain: " + tries);
        if(randomNumber == Integer.parseInt(guessingNumberInput.getText().toString())){
            Toast.makeText(GuessingNumberActivity.this,"You Win!!!",Toast.LENGTH_SHORT).show();
            if(stageNum == 3){
                Toast.makeText(GuessingNumberActivity.this,"You win the game, go fill lottery",Toast.LENGTH_SHORT).show();
                btnGuess.setClickable(false);
                return;
            }
            stageNum++;
            // save stage num to local storage.
            //SharedPreferences sharedPref = GussingNumber.this.getPreferences(Context.MODE_PRIVATE);
            //SharedPreferences.Editor editor = sharedPref.edit();
            resetUpdateGame(stageNum);
        }else{
            Toast.makeText(GuessingNumberActivity.this,"You Wrong!!!",Toast.LENGTH_SHORT).show();
        }
    }
}