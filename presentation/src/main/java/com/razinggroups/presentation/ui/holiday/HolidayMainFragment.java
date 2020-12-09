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
import android.widget.LinearLayout;

import com.razinggroups.presentation.R;

public class HolidayMainFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    LinearLayout DuLayout, IVPLayout;


    public HolidayMainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_holiday_main, container, false);

        DuLayout = view.findViewById(R.id.fragment_holiday_main_du);
        IVPLayout = view.findViewById(R.id.fragment_holiday_main_ivp);
        DuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                HolidayListFragment holidayListFragment = new HolidayListFragment();
                transaction.replace(R.id.activity_main_frame, holidayListFragment);
                transaction.addToBackStack(null);

                Bundle bundle = new Bundle();
                bundle.putString("from", "DU");
                holidayListFragment.setArguments(bundle);

                transaction.commit();
            }
        });
        IVPLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                HolidayCityFragment holidayListFragment = new HolidayCityFragment();
                transaction.replace(R.id.activity_main_frame, holidayListFragment);
                transaction.addToBackStack(null);
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
        void onFragmentInteraction(Uri uri);
    }
}
