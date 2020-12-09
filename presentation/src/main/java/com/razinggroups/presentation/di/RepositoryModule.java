package com.razinggroups.presentation.di;

import android.content.Context;

import com.razinggroups.data.database.SampleRoomDatabase;
import com.razinggroups.data.network.ApiInterface;
import com.razinggroups.data.repository.SampleRepositoryImpl;
import com.razinggroups.data.sharedpreference.SharedPreferenceHelper;
import com.razinggroups.domain.repository.SampleRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides
    @Singleton
    SampleRepository provideSampleRepository(ApiInterface apiInterface, SharedPreferenceHelper sharedPreferenceHelper, SampleRoomDatabase db, Context context) {
        return new SampleRepositoryImpl(apiInterface, sharedPreferenceHelper, db, context);
    }
}
