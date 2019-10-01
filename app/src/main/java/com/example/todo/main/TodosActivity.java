package com.example.todo.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.todo.R;
import com.google.android.material.bottomappbar.BottomAppBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class TodosActivity extends AppCompatActivity {

    @BindView(R.id.bottom_app_bar)
    BottomAppBar bottomAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);
        ButterKnife.bind(this);
        setSupportActionBar(bottomAppBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.todos_option_menu,menu);
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
