package com.example.todo;

import com.example.todo.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainApplication extends DaggerApplication {

    @Inject
    CalligraphyConfig calligraphyConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(calligraphyConfig);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
