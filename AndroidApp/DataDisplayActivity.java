package com.zybooks.finalprojectcs360jorgebermudez;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class DataDisplayActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        dbHelper = new DatabaseHelper(this);

        Button inputButton = findViewById(R.id.addButton);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
        //loadEntries();
    }

    private void showInputDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.input_dialog, null);
        EditText weightInput = dialogView.findViewById(R.id.editTextWeight);
        EditText dateInput = dialogView.findViewById(R.id.editTextDate);

        new AlertDialog.Builder(this)
                .setTitle("Enter Weight and Date")
                .setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    String weight = weightInput.getText().toString();
                    String date = dateInput.getText().toString();
                    saveEntry(weight, date);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void saveEntry(String weight, String date) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("weight", weight);
        values.put("date", date);
        db.insert("weight_entries", null, values);
        db.close();

        //loadEntries(); // Refresh UI after saving loadEntries() to be added in full as work is ex
        //-pected in the third artifact within DatabaseHelper.java
    }
    /*private void loadEntries() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT weight, date FROM weight_entries", null);

        ArrayList<String> entries = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String weight = cursor.getString(0);
                String date = cursor.getString(1);
                entries.add("Weight: " + weight + " lbs\nDate: " + date);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, entries);
        GridView gridView = findViewById(R.id.dataGridView);
        gridView.setAdapter(adapter);///
    }*/

}
