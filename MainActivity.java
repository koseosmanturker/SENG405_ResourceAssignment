package com.example.seng405resourceassignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText firstPlayerName = findViewById(R.id.player1Username);
        EditText secondPlayerName = findViewById(R.id.player2Username);


        Button startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(v -> {
            String firstPlayerUsername = firstPlayerName.getText().toString();
            String secondPlayerUsername = secondPlayerName.getText().toString();

            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("firstPlayer", firstPlayerUsername);
            intent.putExtra("secondPlayer", secondPlayerUsername);
            startActivity(intent);
        });

    }
}