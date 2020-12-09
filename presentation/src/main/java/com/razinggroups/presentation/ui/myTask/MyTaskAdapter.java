package com.razinggroups.presentation.ui.myTask;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.razinggroups.domain.model.myTask.FetchAllMyTaskRecords;
import com.razinggroups.presentation.R;

import java.util.List;

public class MyTaskAdapter extends RecyclerView.Adapter<MyTaskAdapter.MyViewHolder> {
    List<FetchAllMyTaskRecords> dataList;
    public onItemClick onItemClick;

    public MyTaskAdapter(List<FetchAllMyTaskRecords> dataList, MyTaskAdapter.onItemClick onItemClick) {
        this.dataList = dataList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_task_rv_item, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (dataList.get(i) != null && dataList.get(i).getStatus() != null) {
            myViewHolder.taskTv.setText(dataList.get(i).getTaskTitle());
            if (dataList.get(i).getStatus().equalsIgnoreCase("pending")) {
                myViewHolder.statusIv.setImageResource(R.drawable.time);
            } else {
                myViewHolder.statusIv.setImageResource(R.drawable.check_green);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView statusIv;
        private TextView taskTv;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            statusIv = itemView.findViewById(R.id.my_task_rv_item_iv);
            taskTv = itemView.findViewById(R.id.my_task_rv_item_tv);
            taskTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onItemClick(dataList.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    public interface onItemClick {
        public void onItemClick(String taskId);
    }
}