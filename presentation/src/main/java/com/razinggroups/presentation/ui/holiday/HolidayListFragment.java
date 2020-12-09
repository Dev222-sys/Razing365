package com.razinggroups.presentation.ui.holiday;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.razinggroups.presentation.MainApplication;
import com.razinggroups.presentation.R;
import com.razinggroups.presentation.base.BaseFragment;
import com.razinggroups.domain.model.holiday.HolidayRecordResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class HolidayListFragment extends BaseFragment<HolidayViewModel> implements HolidayNavigator {
    @Inject
    @Named("HolidayListFragment")
    ViewModelProvider.Factory viewModelFactory;

    HolidayViewModel holidayViewModel;

    TextView titleTv, errorTv;
    RecyclerView recyclerView;
    String city = "";
    ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        holidayViewModel = ViewModelProviders.of(this, viewModelFactory).get(HolidayViewModel.class);
        holidayViewModel.setNavigator(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_holiday, container, false);
        titleTv = view.findViewById(R.id.fragment_holiday_title_tv);
        errorTv = view.findViewById(R.id.fragment_holiday_title_error_tv);
        errorTv.setVisibility(View.GONE);
        progressBar = view.findViewById(R.id.fragment_holiday_title_progress_bar);

        recyclerView = view.findViewById(R.id.fragment_holiday_rv);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        city = getArguments().getString("from");

        if (city.equalsIgnoreCase("DU")) {
            titleTv.setText("DU Holidays");
        } else {
            titleTv.setText(city.toUpperCase() + " Holidays");
        }

        recyclerView.setVisibility(View.GONE);
        holidayViewModel.fetchList(city);
        return view;
    }

    @Override
    public HolidayViewModel getViewModel() {
        return holidayViewModel;
    }

    @Override
    public void onError(String toString) {
        progressBar.setVisibility(View.GONE);
        errorTv.setVisibility(View.VISIBLE);
        errorTv.setText(toString);
    }

    @Override
    public void onListLoaded(List<HolidayRecordResponse> holidayRecordResponses) {
        progressBar.setVisibility(View.GONE);
        errorTv.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new HolidayAdapter(holidayRecordResponses));
    }
}
