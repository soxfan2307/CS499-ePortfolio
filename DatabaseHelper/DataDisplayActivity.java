package com.zybooks.finalprojectcs360jorgebermudez;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DataDisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private String username;  // Store username for reuse

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        recyclerView = findViewById(R.id.dataRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get username from intent
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        // Load weight entries for this user
        loadEntries();

        // 🔽 Add Button logic
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            Intent addIntent = new Intent(DataDisplayActivity.this, AddWeightActivity.class);
            addIntent.putExtra("username", username);  // Pass username if needed
            startActivity(addIntent);
        });
    }

    // 🔁 Reload data when returning from AddWeightActivity
    @Override
    protected void onResume() {
        super.onResume();
        loadEntries();
    }

    private void loadEntries() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.getWeightsByUsername(username);
        adapter = new DataAdapter(this, cursor);
        recyclerView.setAdapter(adapter);
    }
}
