package com.example.todo.util.sharedprefenceslivedata;

import android.content.SharedPreferences;

public class SharedPreferenceLongLiveData extends SharedPreferenceLiveData<Long>{

    public SharedPreferenceLongLiveData(SharedPreferences prefs, String key, long defValue) {
        super(prefs, key, defValue);
    }

    @Override
    Long getValueFromPreferences(String key, Long defValue) {
        return sharedPrefs.getLong(key, defValue);
    }
}
