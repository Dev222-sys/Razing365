package com.razinggroups.presentation.ui.holiday;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.razinggroups.presentation.R;
import com.razinggroups.domain.model.holiday.HolidayRecordResponse;

import java.util.List;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.MyViewHolder> {
        List<com.razinggroups.domain.model.holiday.HolidayRecordResponse> dataList;

    public HolidayAdapter(List<HolidayRecordResponse> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.holiday_list_item, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.date.setText(dataList.get(i).getDate());
        myViewHolder.name.setText(dataList.get(i).getHolidayName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView date,name;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.holiday_list_item_date);
            name = itemView.findViewById(R.id.holiday_list_item_name);
        }
    }
}