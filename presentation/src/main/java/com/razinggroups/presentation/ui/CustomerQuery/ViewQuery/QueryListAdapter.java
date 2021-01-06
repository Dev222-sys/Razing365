package com.razinggroups.presentation.ui.CustomerQuery.ViewQuery;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.domain.model.leave.FetchAllLeavesRecordResponse;
import com.razinggroups.presentation.R;

import java.util.List;

class QueryListAdapter extends RecyclerView.Adapter<QueryListAdapter.MyViewHolder> {

    List<Customer> dataList;

   // public onItemClick onItemClick;

    public QueryListAdapter(List<Customer> dataList/*, onItemClick onItemClick*/) {

        this.dataList = dataList;
        //this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.query_list_item, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.name.setText(dataList.get(i).getName());
        myViewHolder.email.setText(dataList.get(i).getCompany_email());
        myViewHolder.contact_no.setText(dataList.get(i).getMobile());
        myViewHolder.passport_no.setText(dataList.get(i).getPassport_no());
        myViewHolder.Query.setText(dataList.get(i).getEnquiry_details());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name,email,contact_no,passport_no,Query;
        RelativeLayout layout;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.query_list_name_tv);
            email=itemView.findViewById(R.id.query_list_company_email_tv);
            contact_no=itemView.findViewById(R.id.query_list_contact_no_tv);
            passport_no=itemView.findViewById(R.id.leave_list_to_tv);
            Query=itemView.findViewById(R.id.leave_list_leave_remaining_tv);
            layout=itemView.findViewById(R.id.query_item_layout);



        }
    }

 /*   public interface onItemClick {
        public void onBtnClick(String taskId, String status);


    }*/
}