package com.example.seng405resourceassignment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    TextView timerText;
    long startTime = 0L, player1Time = 0L, player2Time = 0L;
    Handler handler = new Handler();

    int playerTurn = 1;
    long gameTime = 20000L;

    String firstPlayerUsername;
    String secondPlayerUserName;
    Button firstPlayerButton;
    Button secondPlayerButton;

    boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        Intent intent = getIntent();
        firstPlayerUsername = intent.getStringExtra("firstPlayer");
        secondPlayerUserName = intent.getStringExtra("secondPlayer");

        firstPlayerButton = findViewById(R.id.firstButton);
        secondPlayerButton = findViewById(R.id.secondButton);

        firstPlayerButton.setText(firstPlayerUsername);
        secondPlayerButton.setText(secondPlayerUserName);

        timerText = findViewById(R.id.timerTextView);

        secondPlayerButton.setEnabled(false);
        firstPlayerButton.setOnClickListener(view -> {
            if (playerTurn == 1) {
                if (!isTimerRunning) {
                    startTime = System.currentTimeMillis();
                    isTimerRunning = true;
                    secondPlayerButton.setEnabled(false);
                    handler.postDelayed(updateTimerThread, 0);
                }
                else {
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    player1Time += elapsedTime;
                    startTime = 0L;
                    isTimerRunning = false;
                    secondPlayerButton.setEnabled(true);
                    firstPlayerButton.setEnabled(false);
                    handler.removeCallbacks(updateTimerThread);
                    playerTurn = 2;
                }
            }
        });

        secondPlayerButton.setOnClickListener(view -> {
            if (playerTurn == 2) {
                if (!isTimerRunning) {
                    startTime = System.currentTimeMillis();
                    isTimerRunning = true;
                    firstPlayerButton.setEnabled(false);
                    handler.postDelayed(updateTimerThread, 0);
                }
                else {
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    player2Time += elapsedTime;
                    startTime = 0L;
                    isTimerRunning = false;
                    firstPlayerButton.setEnabled(true);
                    secondPlayerButton.setEnabled(false);
                    handler.removeCallbacks(updateTimerThread);
                    playerTurn = 1;
                }
            }
        });


    }

    private void startFinalActivity(int winnerPlayer, String firstPlayerUsername, String secondPlayerUsername) {

        Intent intentFinal = new Intent(GameActivity.this, FinalActivity.class);
        intentFinal.putExtra("firstPlayer", firstPlayerUsername);
        intentFinal.putExtra("secondPlayer", secondPlayerUsername);
        String winner = Integer.toString(winnerPlayer);
        intentFinal.putExtra("winnerPlayer", winner);
        startActivity(intentFinal);
        startTime = 0L;
    }

    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            long currentTime = System.currentTimeMillis() - startTime;
            long totalSeconds = (currentTime + player1Time + player2Time) / 1000;
            long splitSeconds = (currentTime + player1Time + player2Time) % 1000 / 10;
            timerText.setText(String.format("%02d:%02d", totalSeconds, splitSeconds));
            if (totalSeconds >= gameTime / 1000) {
                if (firstPlayerButton.isEnabled()) {
                    startFinalActivity(2, firstPlayerUsername, secondPlayerUserName);
                } else {
                    startFinalActivity(1, firstPlayerUsername, secondPlayerUserName);
                }
            } else {
                handler.postDelayed(this, 100);
            }
        }
    };



}