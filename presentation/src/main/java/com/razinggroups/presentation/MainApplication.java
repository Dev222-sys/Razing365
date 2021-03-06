package com.razinggroups.presentation;

import android.app.Application;

import com.razinggroups.presentation.di.AppModule;
import com.razinggroups.presentation.di.DaggerMyComponent;
import com.razinggroups.presentation.di.MyComponent;

public class MainApplication extends Application {

    private MyComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMyComponent.builder().appModule(new AppModule(getApplicationContext())).build();
    }

    public MyComponent getComponent() {
        return this.component;
    }
}
