package com.razinggroups.presentation.ui.brandCompany.ListScreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.razinggroups.presentation.R;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse;

import java.util.List;

public class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.MyViewHolder> {

    List<com.razinggroups.domain.model.company.FetchAllCompanyDetailsRecordResponse> companyDataList;
    List<com.razinggroups.domain.model.brand.FetchAllBrandDetailsRecordResponse> brandDataList;
    String dataType;


    public onItemClick onItemClick;

    public CompanyListAdapter(List<FetchAllCompanyDetailsRecordResponse> companyDataList, String dataType, CompanyListAdapter.onItemClick onItemClick) {
        this.companyDataList = companyDataList;
        this.dataType = dataType;
        this.onItemClick = onItemClick;
    }

    public CompanyListAdapter(CompanyListAdapter.onItemClick onItemClick, List<FetchAllBrandDetailsRecordResponse> brandDataList, String dataType) {
        this.brandDataList = brandDataList;
        this.dataType = dataType;
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
        if (dataType.equalsIgnoreCase("company")) {
            myViewHolder.nameTv.setText(companyDataList.get(i).getCompanyName());
            myViewHolder.companyTv.setVisibility(View.GONE);
            myViewHolder.iv.setVisibility(View.GONE);
            myViewHolder.nameTv.setPadding(8, 8, 8, 8);

        } else if (dataType.equalsIgnoreCase("brand")) {
            myViewHolder.nameTv.setText(brandDataList.get(i).getBrandName());
            myViewHolder.companyTv.setVisibility(View.VISIBLE);
            myViewHolder.companyTv.setText(brandDataList.get(i).getBrandCompany());
            myViewHolder.iv.setVisibility(View.VISIBLE);
            myViewHolder.iv.setImageResource(R.drawable.building_grey);
        }


    }

    @Override
    public int getItemCount() {
        if (dataType.equalsIgnoreCase("company"))
            return companyDataList.size();
        else if (dataType.equalsIgnoreCase("brand"))
            return brandDataList.size();

        return 0;
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
                    if (dataType.equalsIgnoreCase("company"))
                        onItemClick.onCompanyItemClick(companyDataList.get(getAdapterPosition()).getId());
                    else if (dataType.equalsIgnoreCase("brand"))
                        onItemClick.onBrandItemClick(brandDataList.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    public interface onItemClick {
        public void onCompanyItemClick(String id);

        void onBrandItemClick(String id);
    }
}