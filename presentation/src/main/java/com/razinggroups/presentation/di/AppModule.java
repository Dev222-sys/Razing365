package com.razinggroups.presentation.di;

import android.content.Context;

import com.razinggroups.data.database.SampleRoomDatabase;
import com.razinggroups.data.network.ApiClient;
import com.razinggroups.data.network.ApiInterface;
import com.razinggroups.data.sharedpreference.SharedPreferenceHelper;
import com.razinggroups.domain.executor.PostExecutionThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return this.context;
    }

    @Provides
    @Singleton
    SharedPreferenceHelper provideSharedPreferenceHelper() {
        return new SharedPreferenceHelper(this.context);
    }

    @Provides
    @Singleton
    SampleRoomDatabase provideWordRoomDatabase(Context context) {
        return SampleRoomDatabase.getDatabase(this.context);
    }

    @Provides
    @Singleton
    ApiInterface provideApiInterface() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }
}
