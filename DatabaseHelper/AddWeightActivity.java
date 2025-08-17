package com.zybooks.finalprojectcs360jorgebermudez;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddWeightActivity extends AppCompatActivity {

    private EditText weightInput, dateInput;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight);

        weightInput = findViewById(R.id.weightInput);
        dateInput = findViewById(R.id.dateInput);
        // Auto-fill date field with today's date
        String todayDate = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(new java.util.Date());
        dateInput.setText(todayDate);

        Button saveButton = findViewById(R.id.saveButton);

        dbHelper = new DatabaseHelper(this);

        saveButton.setOnClickListener(v -> {
            try {
                float weight = Float.parseFloat(weightInput.getText().toString());
                String date = dateInput.getText().toString();

                SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
                int userId = prefs.getInt("userId", -1);

                if (userId != -1) {
                    dbHelper.insertWeightEntry(date, weight, userId);
                    Toast.makeText(this, "Weight entry saved!", Toast.LENGTH_SHORT).show();
                    finish(); // go back to DataDisplayActivity
                } else {
                    Toast.makeText(this, "User session error", Toast.LENGTH_SHORT).show();
                }

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter a valid weight", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
