package com.example.todo.util.sharedprefenceslivedata;

import android.content.SharedPreferences;

public class SharedPreferenceBooleanLiveData extends SharedPreferenceLiveData<Boolean>{

    public SharedPreferenceBooleanLiveData(SharedPreferences prefs, String key, Boolean defValue) {
        super(prefs, key, defValue);
    }

    @Override
    Boolean getValueFromPreferences(String key, Boolean defValue) {
        return sharedPrefs.getBoolean(key, defValue);
    }
}
