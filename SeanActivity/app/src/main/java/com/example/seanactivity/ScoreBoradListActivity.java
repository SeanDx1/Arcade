package com.example.seanactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ScoreBoradListActivity extends AppCompatActivity {
    private ListView listView;
    private AdapterClass listAdapter;
    private ArrayList<ScoreBoard> score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_borad_list);
        score = new ArrayList<ScoreBoard>();
        ScoreBoard a = new ScoreBoard("TicTacToe",R.drawable.tictactoelogo1);
        ScoreBoard b = new ScoreBoard("GuessingNumber",R.drawable.guessing_num);
        ScoreBoard c = new ScoreBoard("SevenBoom",R.drawable.boom);
        score.add(a);
        score.add(b);
        score.add(c);

        listView = findViewById(R.id.listview);

        listAdapter = new AdapterClass(getApplicationContext(),score);
        listView.setAdapter(listAdapter);

    }
}