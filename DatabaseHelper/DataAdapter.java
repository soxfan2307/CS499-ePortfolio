package com.zybooks.finalprojectcs360jorgebermudez;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private Cursor cursor;
    private Context context;

    public DataAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView weightTextView;
        public TextView dateTextView;

        public ViewHolder(View view) {
            super(view);
            weightTextView = view.findViewById(R.id.weightTextView);
            dateTextView = view.findViewById(R.id.dateTextView);
        }
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            float weight = cursor.getFloat(cursor.getColumnIndexOrThrow("weight"));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            holder.weightTextView.setText("Weight: " + weight);
            holder.dateTextView.setText("Date: " + date);
        }
    }

    @Override
    public int getItemCount() {
        return (cursor != null) ? cursor.getCount() : 0;
    }
}