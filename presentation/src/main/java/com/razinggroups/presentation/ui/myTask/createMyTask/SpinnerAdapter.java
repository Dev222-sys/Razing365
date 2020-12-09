package com.razinggroups.presentation.ui.myTask.createMyTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.razinggroups.presentation.R;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<ItemData> {


    Activity context;
    List<ItemData> list;
    LayoutInflater inflater;

    public SpinnerAdapter(Activity context , int id, List<ItemData> list) {
        super(context, id, list);
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public boolean isEnabled(int position) {
        if (position == 0) {
            // Disable the first item from Spinner
            // First item will be use for hint
            return false;
        } else {
            return true;
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.spinner_item, parent, false);
        TextView textView = (TextView) itemView.findViewById(R.id.txt);
        textView.setText(list.get(position).getName());

        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup
            parent) {

        View view = getView(position, convertView, parent);
        TextView tv = (TextView) view.findViewById(R.id.txt);
        if (position == 0) {
            tv.setTextColor(Color.GRAY);
        } else {
            tv.setTextColor(Color.BLACK);
        }
        return view;

    }
}