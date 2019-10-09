package com.example.todo.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.todo.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class TodosActivity extends AppCompatActivity {

    @BindView(R.id.bottom_app_bar)
    BottomAppBar bottomAppBar;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.main_todo_frameLayout)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);
        ButterKnife.bind(this);
        setSupportActionBar(bottomAppBar);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTodoBottomSheerFragment addTodoBottomSheerFragment = new AddTodoBottomSheerFragment();
                addTodoBottomSheerFragment.show(getSupportFragmentManager(),null);
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_todo_frameLayout,new TodosFragment());
        transaction.commit();
    }


    private boolean isSameDay(Date date1, Date date2){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd", Locale.US);
        return fmt.format(date1).equals(fmt.format(date2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.todos_option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.options) {
            TodoOptionBottomSheetFragment bottomSheetFragment = new TodoOptionBottomSheetFragment();
            bottomSheetFragment.show(getSupportFragmentManager(), null);
            return true;
        }

        if (item.getItemId() == android.R.id.home){
            NavigationBottomSheetFragment navigationBottomSheetFragment = new NavigationBottomSheetFragment();
            navigationBottomSheetFragment.show(getSupportFragmentManager(),null);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
