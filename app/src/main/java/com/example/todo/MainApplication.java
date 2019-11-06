package com.example.todo;

import android.content.SharedPreferences;

import com.example.todo.db.entity.TodoCategoryEntity;
import com.example.todo.di.DaggerAppComponent;
import com.example.todo.util.SharedConstants;
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

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(calligraphyConfig);

        if (sharedPreferences.getBoolean(SharedConstants.FIRST_START,true)){
            todoCategoryViewModel.insertCategory(initialTodoCategory);
            sharedPreferences.edit().putBoolean(SharedConstants.FIRST_START,false).apply();
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
