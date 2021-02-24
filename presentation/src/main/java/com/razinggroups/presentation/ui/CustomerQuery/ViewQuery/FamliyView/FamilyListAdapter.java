package com.razinggroups.presentation.ui.CustomerQuery.ViewQuery.FamliyView;

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

import com.razinggroups.domain.model.CustomerQuery.FamilyDetails;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.ui.CustomerQuery.ViewQuery.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

class FamilyListAdapter extends RecyclerView.Adapter<FamilyListAdapter.MyViewHolder> implements Filterable {

    ArrayList<FamilyDetails> dataList;
    ItemClickListener itemClickListener;

    List<FamilyDetails> mDataFiltered ;

    Context context;


    // String usertype = SharedPrefManager.getInstans(context).getLogintype();
    //String username = SharedPrefManager.getInstans(this).getUsername();

    // public onItemClick onItemClick;

    public FamilyListAdapter(ArrayList<FamilyDetails> dataList, ItemClickListener itemClickListene) {


        this.dataList = dataList;
        this.itemClickListener = itemClickListene;

        this.mDataFiltered = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.family_item_layout, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.name.setText(mDataFiltered.get(i).getFull_name());
        myViewHolder.age.setText(mDataFiltered.get(i).getAge());

        myViewHolder.passport_no.setText(mDataFiltered.get(i).getPassport_no());
    //    myViewHolder.Query.setText(mDataFiltered.get(i).getEnquiry_details());
        /*if (usertype.equals("MasterAdmin"))
        {

            myViewHolder.button.setVisibility(View.VISIBLE);
        }*/

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
                    List<FamilyDetails> lstFiltered = new ArrayList<>();
                    for (FamilyDetails row : dataList) {

                        if (row.getFull_name().toLowerCase().contains(Key.toLowerCase()) || row.getUpdate_at().toLowerCase().contains(Key.toLowerCase())){

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
                mDataFiltered = (List<FamilyDetails>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        private TextView name,age,contact_no,passport_no,Query;
        RelativeLayout layout;
        Button button;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.family_list_name_tv);
            age=itemView.findViewById(R.id.family_list_age);

            passport_no=itemView.findViewById(R.id.familymemberPassport_no);

            layout=itemView.findViewById(R.id.query_item_layout);
            //layout.setOnClickListener(this);
            button=itemView.findViewById(R.id.familylist_view_btn);

            button.setOnClickListener(this);

            /*layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });*/



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