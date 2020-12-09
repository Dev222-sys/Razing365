package com.razinggroups.presentation.ui.holiday;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.razinggroups.presentation.R;


public class HolidayCityFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    TextView delhi, mumbai, bangalore, kolkata, sriLanka;

    public HolidayCityFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_holiday_city, container, false);
        delhi = view.findViewById(R.id.fragment_holiday_city_delhi);
        mumbai = view.findViewById(R.id.fragment_holiday_city_mumbai);
        bangalore = view.findViewById(R.id.fragment_holiday_city_bangalore);
        kolkata = view.findViewById(R.id.fragment_holiday_city_kolkata);
        sriLanka = view.findViewById(R.id.fragment_holiday_city_sri_lanka);

        delhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                HolidayListFragment holidayListFragment = new HolidayListFragment();
                transaction.replace(R.id.activity_main_frame, holidayListFragment);
                transaction.addToBackStack(null);

                Bundle bundle=new Bundle();
                bundle.putString("from", "delhi");
                holidayListFragment.setArguments(bundle);

                transaction.commit();
            }
        });
        sriLanka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                HolidayListFragment holidayListFragment = new HolidayListFragment();
                transaction.replace(R.id.activity_main_frame, holidayListFragment);
                transaction.addToBackStack(null);

                Bundle bundle=new Bundle();
                bundle.putString("from", "sriLanka");
                holidayListFragment.setArguments(bundle);

                transaction.commit();
            }
        });
        mumbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                HolidayListFragment holidayListFragment = new HolidayListFragment();
                transaction.replace(R.id.activity_main_frame, holidayListFragment);
                transaction.addToBackStack(null);

                Bundle bundle=new Bundle();
                bundle.putString("from", "mumbai");
                holidayListFragment.setArguments(bundle);

                transaction.commit();
            }
        });
        bangalore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                HolidayListFragment holidayListFragment = new HolidayListFragment();
                transaction.replace(R.id.activity_main_frame, holidayListFragment);
                transaction.addToBackStack(null);

                Bundle bundle=new Bundle();
                bundle.putString("from", "bangalore");
                holidayListFragment.setArguments(bundle);

                transaction.commit();
            }
        });
        kolkata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                HolidayListFragment holidayListFragment = new HolidayListFragment();
                transaction.replace(R.id.activity_main_frame, holidayListFragment);
                transaction.addToBackStack(null);

                Bundle bundle=new Bundle();
                bundle.putString("from", "kolkata");
                holidayListFragment.setArguments(bundle);

                transaction.commit();
            }
        });


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
