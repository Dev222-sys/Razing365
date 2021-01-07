package com.razinggroups.presentation.ui.CustomerQuery.ViewQuery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.razinggroups.data.sharedpreference.SharedPrefManager;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.domain.model.leave.FetchAllLeavesRecordResponse;
import com.razinggroups.presentation.R;

import java.util.ArrayList;
import java.util.List;

class QueryListAdapter extends RecyclerView.Adapter<QueryListAdapter.MyViewHolder> implements Filterable {

    ArrayList<Customer> dataList;
    ItemClickListener itemClickListener;

    List<Customer> mDataFiltered ;

    Context context;


    String usertype = SharedPrefManager.getInstans(context).getLogintype();
    //String username = SharedPrefManager.getInstans(this).getUsername();

   // public onItemClick onItemClick;

    public QueryListAdapter(ArrayList<Customer> dataList,ItemClickListener itemClickListene) {


        this.dataList = dataList;
        this.itemClickListener = itemClickListene;

        this.mDataFiltered = dataList;
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

        myViewHolder.name.setText(mDataFiltered.get(i).getName());
        myViewHolder.email.setText(mDataFiltered.get(i).getCompany_email());

        String landline=mDataFiltered.get(i).getLandline();
        if (landline.isEmpty())
        {

            myViewHolder.contact_no.setText(mDataFiltered.get(i).getMobile());
        }
        else {

            myViewHolder.contact_no.setText(mDataFiltered.get(i).getMobile()+ " , "+mDataFiltered.get(i).getLandline());
        }

        myViewHolder.passport_no.setText(mDataFiltered.get(i).getPassport_no());
        myViewHolder.Query.setText(mDataFiltered.get(i).getEnquiry_details());
        if (usertype.equals("MasterAdmin"))
        {

            myViewHolder.button.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mDataFiltered.size();
    }

    @Override
    public Filter getFilter() {

        return  new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                if (Key.isEmpty()) {
                    mDataFiltered = dataList ;
                }
                else {
                    List<Customer> lstFiltered = new ArrayList<>();
                    for (Customer row : dataList) {

                        if (row.getName().toLowerCase().contains(Key.toLowerCase()) || row.getMobile().toLowerCase().contains(Key.toLowerCase())){

                            lstFiltered.add(row);
                        }

                    }

                    mDataFiltered = lstFiltered;

                }


                FilterResults filterResults = new FilterResults();
                filterResults.values= mDataFiltered;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDataFiltered = (List<Customer>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        private TextView name,email,contact_no,passport_no,Query;
        RelativeLayout layout;
        Button button;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.query_list_name_tv);
            email=itemView.findViewById(R.id.query_list_company_email_tv);
            contact_no=itemView.findViewById(R.id.query_list_contact_no_tv);
            passport_no=itemView.findViewById(R.id.leave_list_to_tv);
            Query=itemView.findViewById(R.id.leave_list_leave_remaining_tv);
            layout=itemView.findViewById(R.id.query_item_layout);
            //layout.setOnClickListener(this);
            button=itemView.findViewById(R.id.leave_list_reject_btn);
            button.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {

           itemClickListener.onClick(v,getAdapterPosition(),false);
        }
    }

 /*   public interface onItemClick {
        public void onBtnClick(String taskId, String status);


    }*/
}