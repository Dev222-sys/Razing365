package com.razinggroups.presentation.ui.employeeHomeScreen.EmployeeTaskDetailList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.razinggroups.presentation.R;
import com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord;

import java.util.List;

public class EmployeeTaskDetailListAdapter extends RecyclerView.Adapter<EmployeeTaskDetailListAdapter.MyViewHolder> {
    List<com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord> dataList;
    public onItemClick onItemClick;

    public EmployeeTaskDetailListAdapter(List<FetchAllEmployeeTaskRecord> dataList, EmployeeTaskDetailListAdapter.onItemClick onItemClick) {
        this.dataList = dataList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee_task_details_list_item, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(dataList.get(i).getTaskTitle());
        myViewHolder.detail.setText(dataList.get(i).getTaskDetail());
        myViewHolder.assigned.setText(dataList.get(i).getAssignDate());
        myViewHolder.deadline.setText(dataList.get(i).getDeadline());
        if (dataList.get(i).getStatus().equalsIgnoreCase("completed")) {
            myViewHolder.btn.setText("Task Completed");
            myViewHolder.btn.setClickable(false);
            myViewHolder.btn.setBackgroundColor(myViewHolder.btn.getContext().getResources().getColor(R.color.colorPrimary));
        } else {
            myViewHolder.btn.setText("Mark Completed");
            myViewHolder.btn.setClickable(true);
            myViewHolder.btn.setBackgroundColor(myViewHolder.btn.getContext().getResources().getColor(R.color.colorRed));

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
            title = itemView.findViewById(R.id.employee_task_detail_list_item_title);
            detail = itemView.findViewById(R.id.employee_task_detail_list_item_detail);
            assigned = itemView.findViewById(R.id.employee_task_detail_list_item_assigned);
            deadline = itemView.findViewById(R.id.employee_task_detail_list_item_deadline);
            btn = itemView.findViewById(R.id.employee_task_detail_list_item_btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onBtnClick(dataList.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    public interface onItemClick {
        public void onBtnClick(String taskId);
    }
}