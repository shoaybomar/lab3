package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        TextView welcomeTextView = findViewById(R.id.textViewWelcome);
        Button thankYouButton = findViewById(R.id.buttonThankYou);
        Button dontCallMeThatButton = findViewById(R.id.buttonDontCallMeThat);

        Intent intent = getIntent();
        if (intent != null) {
            String userName = intent.getStringExtra("user_name");
            welcomeTextView.setText(getString(R.string.welcome, userName));
        }

        thankYouButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the result code to 1 (happy) and return to MainActivity
                setResult(RESULT_OK, new Intent().putExtra("result_code", 1));
                finish();
            }
        });

        dontCallMeThatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the result code to 0 (change name) and return to MainActivity
                setResult(RESULT_OK, new Intent().putExtra("result_code", 0));
                finish();
            }
        });
    }
}