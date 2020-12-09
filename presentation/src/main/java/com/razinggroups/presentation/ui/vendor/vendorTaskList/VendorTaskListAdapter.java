package com.razinggroups.presentation.ui.vendor.vendorTaskList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.razinggroups.presentation.R;
import com.razinggroups.domain.model.vendorTask.FetchVendorTaksRecord;

import java.util.List;

class VendorTaskListAdapter extends RecyclerView.Adapter<VendorTaskListAdapter.MyViewHolder> {

    List<com.razinggroups.domain.model.vendorTask.FetchVendorTaksRecord> dataList;
    public onItemClick onItemClick;


    public VendorTaskListAdapter(List<FetchVendorTaksRecord> records, onItemClick onItemClick1) {
        this.dataList = records;
        this.onItemClick = onItemClick1;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vendor_task_list_item, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.brandTv.setText(dataList.get(i).getBrand());
        myViewHolder.serviceTv.setText(dataList.get(i).getService());
        myViewHolder.taskTv.setText(dataList.get(i).getTaskDetail());
        myViewHolder.assignTv.setText(dataList.get(i).getAssignDate());
        myViewHolder.deadlineTv.setText(dataList.get(i).getDeadlineDate());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView brandTv, serviceTv, taskTv, assignTv, deadlineTv;
        private Button btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            brandTv = itemView.findViewById(R.id.vendor_task_list_item_brand);
            serviceTv = itemView.findViewById(R.id.vendor_task_list_item_service);
            taskTv = itemView.findViewById(R.id.vendor_task_list_item_task);
            assignTv = itemView.findViewById(R.id.vendor_task_list_item_assigned);
            deadlineTv = itemView.findViewById(R.id.vendor_task_list_item_deadline);
            btn = itemView.findViewById(R.id.vendor_task_list_item_btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onItemClick.onDeleteItemClick(dataList.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    public interface onItemClick {
        void onDeleteItemClick(String id);
    }
}