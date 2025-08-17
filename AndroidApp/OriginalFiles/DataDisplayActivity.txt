package com.zybooks.finalprojectcs360jorgebermudez;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class DataDisplayActivity extends AppCompatActivity {
    private GridView dataGridView;
    private Button addButton;

    private DatabaseHelper databaseHelper;
    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        databaseHelper = new DatabaseHelper(this);

        dataGridView = findViewById(R.id.dataGridView);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle add button click event
            }
        });

        Cursor cursor = databaseHelper.getAllWeightEntries();
        dataAdapter = new DataAdapter(this, cursor);
        dataGridView.setAdapter(dataAdapter);
    }
}

