package com.example.seng405resourceassignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent intent = getIntent();
        String firstPlayerUsername = intent.getStringExtra("firstPlayer");
        String secondPlayerUserName = intent.getStringExtra("secondPlayer");
        String winnerPlayer = intent.getStringExtra("winnerPlayer");

        showWinnerName(winnerPlayer,firstPlayerUsername,secondPlayerUserName);

        Button restartButton = findViewById(R.id.restartButton);

        Button restartFromBeginningButton = findViewById(R.id.restartButtonWithNewNames);

        restartButton.setOnClickListener(view -> {
            Intent intentGame = new Intent(FinalActivity.this, GameActivity.class);
            intentGame.putExtra("firstPlayer", firstPlayerUsername);
            intentGame.putExtra("secondPlayer", secondPlayerUserName);
            startActivity(intentGame);
        });

        restartFromBeginningButton.setOnClickListener(view -> {
            Intent intentStart = new Intent(FinalActivity.this, MainActivity.class);
            startActivity(intentStart);
        });


    }

    private void showWinnerName(String winnerPlayer, String firstPlayerUsername, String secondPlayerUserName) {

        TextView winnerText = findViewById(R.id.winnerTextView);

        if(winnerPlayer.equals("1")){
            winnerText.setText(firstPlayerUsername);
        }
        else{
            winnerText.setText(secondPlayerUserName);
        }

    }
}