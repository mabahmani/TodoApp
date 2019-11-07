package com.example.todo.main;

import android.content.SharedPreferences;

import com.example.todo.util.SharedConstants;
import com.example.todo.util.sharedprefenceslivedata.SharedPreferenceLongLiveData;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class TodosFragmentModule {

    @Provides
    static SharedPreferenceLongLiveData provideSharedPreferenceLongLiveDataActiveCategory(SharedPreferences sharedPreferences) {
        return new SharedPreferenceLongLiveData(sharedPreferences, SharedConstants.ACTIVE_CATEGORY,-1);
    }
}
