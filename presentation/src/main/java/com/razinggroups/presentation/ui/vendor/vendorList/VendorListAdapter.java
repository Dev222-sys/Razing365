package com.razinggroups.presentation.ui.vendor.vendorList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.razinggroups.presentation.R;
import com.razinggroups.domain.model.vendor.FetchAllVendorRecordResponse;

import java.util.List;

public class VendorListAdapter extends RecyclerView.Adapter<VendorListAdapter.MyViewHolder> {

    List<com.razinggroups.domain.model.vendor.FetchAllVendorRecordResponse> dataList;
    public onItemClick onItemClick;


    public VendorListAdapter(List<FetchAllVendorRecordResponse> records, onItemClick onItemClick1) {
        this.dataList = records;
        this.onItemClick = onItemClick1;
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
        myViewHolder.nameTv.setText(dataList.get(i).getFirstName() + " " + dataList.get(i).getMiddleName() + " " + dataList.get(i).getLastName());
        myViewHolder.companyTv.setText(dataList.get(i).getVendorBrand());
        myViewHolder.iv.setImageResource(R.drawable.building_grey);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv, companyTv;
        private ImageView iv;
        private RelativeLayout layout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.employee_item_employee_name_tv);
            iv = itemView.findViewById(R.id.employee_item_calender_iv);
            companyTv = itemView.findViewById(R.id.employee_item_company_name_tv);
            layout = itemView.findViewById(R.id.employee_item_layout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onItemClick.onVendorItemClick(dataList.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    public interface onItemClick {
        void onVendorItemClick(String id);
    }
}
