package com.razinggroups.presentation.ui.employee;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.razinggroups.presentation.R;
import com.razinggroups.domain.model.employee.EmployeeDetail;

import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.MyViewHolder> {

    List<com.razinggroups.domain.model.employee.EmployeeDetail> dataList;
    public onItemClick onItemClick;

    public EmployeeListAdapter(List<EmployeeDetail> dataList, EmployeeListAdapter.onItemClick onItemClick) {
        this.dataList = dataList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee_list_item, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.nameTv.setText(dataList.get(i).getName());
        myViewHolder.companyTv.setText(dataList.get(i).getBrandName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv,companyTv;
        private RelativeLayout layout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.employee_item_employee_name_tv);
            companyTv = itemView.findViewById(R.id.employee_item_company_name_tv);
            layout = itemView.findViewById(R.id.employee_item_layout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onItemClick(dataList.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    public interface onItemClick {
        public void onItemClick(String empId);
    }
}