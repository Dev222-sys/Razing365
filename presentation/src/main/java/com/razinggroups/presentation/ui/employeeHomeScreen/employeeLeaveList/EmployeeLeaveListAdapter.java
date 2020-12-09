package com.razinggroups.presentation.ui.employeeHomeScreen.employeeLeaveList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.razinggroups.presentation.R;
import com.razinggroups.domain.model.leave.FetchSingleEmployeeLeaveRecord;

import java.util.List;

public class EmployeeLeaveListAdapter extends RecyclerView.Adapter<EmployeeLeaveListAdapter.MyViewHolder> {

    List<com.razinggroups.domain.model.leave.FetchSingleEmployeeLeaveRecord> dataList;

    public EmployeeLeaveListAdapter(List<FetchSingleEmployeeLeaveRecord> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee_leave_list_item, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(dataList.get(i).getLeaveTitle());
        myViewHolder.detail.setText(dataList.get(i).getDiscription());
        myViewHolder.assigned.setText(dataList.get(i).getFromDate());
        myViewHolder.deadline.setText(dataList.get(i).getToDate());
        if (dataList.get(i).getStatus().equalsIgnoreCase("rejected")) {
            myViewHolder.btn.setText("Rejected");
            myViewHolder.btn.setBackgroundColor(myViewHolder.btn.getContext().getResources().getColor(R.color.colorRed));
        } else if (dataList.get(i).getStatus().equalsIgnoreCase("pending")) {
            myViewHolder.btn.setText("Pending");
            myViewHolder.btn.setBackgroundColor(myViewHolder.btn.getContext().getResources().getColor(R.color.colorYellow));
        } else {
            myViewHolder.btn.setText("Approved");
            myViewHolder.btn.setBackgroundColor(myViewHolder.btn.getContext().getResources().getColor(R.color.colorPrimary));
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, detail, assigned, deadline;
        Button btn;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.employee_leave_list_item_title);
            detail = itemView.findViewById(R.id.employee_leave_list_item_desc);
            assigned = itemView.findViewById(R.id.employee_leave_list_item_start_date);
            deadline = itemView.findViewById(R.id.employee_leave_list_item_end_date);
            btn = itemView.findViewById(R.id.employee_leave_list_item_btn);

        }
    }
}