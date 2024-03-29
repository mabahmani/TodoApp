package com.example.todo.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.app.DialogCompat;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.todo.R;
import com.example.todo.main.TodosActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (!isNetworkConnected()){
            AppCompatDialog dialog = new AppCompatDialog(SplashActivity.this);
            dialog.setContentView(LayoutInflater.from(this).inflate(R.layout.dialoog_no_internet,null,false));
            dialog.show();
        }

        Intent intent = new Intent(SplashActivity.this, TodosActivity.class);
        startActivity(intent);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(SplashActivity.CONNECTIVITY_SERVICE);
        return (cm != null ? cm.getActiveNetworkInfo() : null) != null && cm.getActiveNetworkInfo().isConnected();
    }
}
