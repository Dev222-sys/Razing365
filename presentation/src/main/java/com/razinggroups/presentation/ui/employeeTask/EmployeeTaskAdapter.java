package com.razinggroups.presentation.ui.employeeTask;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.razinggroups.presentation.R;
import com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord;

import java.util.List;

public class EmployeeTaskAdapter extends RecyclerView.Adapter<EmployeeTaskAdapter.MyViewHolder> {
    List<com.razinggroups.domain.model.employeeTask.FetchAllEmployeeTaskRecord> dataList;
    public onItemClick onItemClick;

    public EmployeeTaskAdapter(List<FetchAllEmployeeTaskRecord> dataList, EmployeeTaskAdapter.onItemClick onItemClick) {
        this.dataList = dataList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee_task_item, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.taskTv.setText(dataList.get(i).getTaskTitle());
        myViewHolder.nameTv.setText(dataList.get(i).getEmpname());
        if(dataList.get(i).getStatus().equalsIgnoreCase("pending"))
        {
            myViewHolder.statusIv.setImageResource(R.drawable.time);
        }
        else
        {
            myViewHolder.statusIv.setImageResource(R.drawable.check_green);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView statusIv;
        private TextView taskTv, nameTv;

        private RelativeLayout layout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            statusIv = itemView.findViewById(R.id.employee_task_item_status_iv);
            taskTv = itemView.findViewById(R.id.employee_task_item_task_tv);
            nameTv = itemView.findViewById(R.id.employee_task_item_employee_name_tv);
            layout = itemView.findViewById(R.id.employee_task_item_layout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onItemClick(dataList.get(getAdapterPosition()).getId(), dataList.get(getAdapterPosition()).getEmpid());
                }
            });
        }
    }

    public interface onItemClick {
        public void onItemClick(String taskId, String empId);
    }
}
