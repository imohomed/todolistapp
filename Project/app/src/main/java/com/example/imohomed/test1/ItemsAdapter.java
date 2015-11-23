package com.example.imohomed.test1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by i.mohomed on 11/23/15.
 */
public class ItemsAdapter extends ArrayAdapter<Item> {
    public ItemsAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Item user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        // Lookup view for data population
        TextView tvText = (TextView) convertView.findViewById(R.id.tvText);
        TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);
        // Populate the data into the template view using the data object
        tvText.setText(user.text);
        if (user.priority != null) {
            switch (user.priority) {
                case "Medium":
                    tvPriority.setTextColor(Color.MAGENTA);
                    tvPriority.setText(user.priority);
                    break;
                case "High":
                    tvPriority.setTextColor(Color.RED);
                    tvPriority.setText(user.priority);
                    break;
                case "Normal":
                    tvPriority.setTextColor(Color.BLACK);
                    tvPriority.setText(user.priority);
                    break;
            }
        }
        // Return the completed view to render on screen
        return convertView;
    }
}