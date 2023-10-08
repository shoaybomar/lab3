package com.example.lab3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        Button buttonNext = findViewById(R.id.buttonNext);

        sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);

        String savedName = sharedPreferences.getString("userName", "");
        editTextName.setText(savedName);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredName = editTextName.getText().toString();

                Intent intent = new Intent(MainActivity.this, NameActivity.class);
                intent.putExtra("userName", enteredName);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        String currentName = editTextName.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", currentName);
        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // User is happy, you can finish the app
                finish();
            } else if (resultCode == RESULT_CANCELED) {
                // User wants to change their name
                String newName = data.getStringExtra("newName");
                editTextName.setText(newName);
            }
        }
    }
}