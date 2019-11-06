package com.example.todo;

import android.content.SharedPreferences;

import com.example.todo.db.entity.TodoCategoryEntity;
import com.example.todo.di.DaggerAppComponent;
import com.example.todo.viewmodel.TodoCategoryViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainApplication extends DaggerApplication {

    @Inject
    CalligraphyConfig calligraphyConfig;
    @Inject
    TodoCategoryEntity initialTodoCategory;
    @Inject
    TodoCategoryViewModel todoCategoryViewModel;
    @Inject
    SharedPreferences sharedPreferences;

    static SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();

        sp = sharedPreferences;

        CalligraphyConfig.initDefault(calligraphyConfig);

    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    public static SharedPreferences getSharedPreferences(){
        return sp;
    }
}
