package com.zybooks.finalprojectcs360jorgebermudez;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class DataAdapter extends CursorAdapter {
    public DataAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate the layout for each grid item view
        return LayoutInflater.from(context).inflate(R.layout.grid_item_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Retrieve data from the cursor and populate the grid item view
        TextView dataTextView = view.findViewById(R.id.dataTextView);

        String data = cursor.getString(cursor.getColumnIndexOrThrow("column_name"));
        dataTextView.setText(data);
    }
}
