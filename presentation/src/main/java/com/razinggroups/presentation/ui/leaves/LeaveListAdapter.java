package com.razinggroups.presentation.ui.leaves;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.razinggroups.domain.model.leave.FetchAllLeavesRecordResponse;
import com.razinggroups.presentation.R;

import java.util.List;

class LeaveListAdapter extends RecyclerView.Adapter<LeaveListAdapter.MyViewHolder> {

    List<FetchAllLeavesRecordResponse> dataList;
    public onItemClick onItemClick;

    public LeaveListAdapter(List<FetchAllLeavesRecordResponse> dataList, onItemClick onItemClick) {
        this.dataList = dataList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leave_list_item, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(dataList.get(i).getLeaveTitle());
        myViewHolder.detail.setText(dataList.get(i).getDiscription());
        myViewHolder.assigned.setText(dataList.get(i).getFromDate());
        myViewHolder.deadline.setText(dataList.get(i).getToDate());
        myViewHolder.name.setText(dataList.get(i).getName());
        myViewHolder.companyName.setText(dataList.get(i).getBrand());
        myViewHolder.leaveRemaining.setText(dataList.get(i).getRemainingLeave());


        if (dataList.get(i).getLeaveStatus().equalsIgnoreCase("rejected")) {
            myViewHolder.approveBtn.setText("Rejected");
            myViewHolder.approveBtn.setBackgroundColor(myViewHolder.approveBtn.getContext().getResources().getColor(R.color.colorPrimary));
            myViewHolder.rejectBtn.setVisibility(View.INVISIBLE);
        } else if (dataList.get(i).getLeaveStatus().equalsIgnoreCase("pending")) {
            myViewHolder.approveBtn.setText("Approve");
            myViewHolder.approveBtn.setBackgroundColor(myViewHolder.approveBtn.getContext().getResources().getColor(R.color.colorGreen));
            myViewHolder.rejectBtn.setVisibility(View.VISIBLE);
        } else {
            myViewHolder.approveBtn.setText("Approved");
            myViewHolder.approveBtn.setBackgroundColor(myViewHolder.approveBtn.getContext().getResources().getColor(R.color.colorYellow));
            myViewHolder.rejectBtn.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, detail, assigned, deadline, name, companyName, leaveRemaining;
        Button rejectBtn, approveBtn;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.leave_list_title_tv);
            detail = itemView.findViewById(R.id.leave_list_desc_tv);
            assigned = itemView.findViewById(R.id.leave_list_from_tv);
            deadline = itemView.findViewById(R.id.leave_list_to_tv);
            name = itemView.findViewById(R.id.leave_list_name_tv);
            companyName = itemView.findViewById(R.id.leave_list_company_name_tv);
            leaveRemaining = itemView.findViewById(R.id.leave_list_leave_remaining_tv);
            rejectBtn = itemView.findViewById(R.id.leave_list_reject_btn);
            approveBtn = itemView.findViewById(R.id.leave_list_approve_btn);

            rejectBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onBtnClick(dataList.get(getAdapterPosition()).getId(), "2");
                }
            });

            approveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onBtnClick(dataList.get(getAdapterPosition()).getId(), "1");
                }
            });


        }
    }

    public interface onItemClick {
        public void onBtnClick(String taskId, String status);


    }
}