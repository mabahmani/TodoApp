package com.example.todo.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.example.todo.R;
import com.example.todo.db.entity.TodoCategoryEntity;
import com.example.todo.util.SharedConstants;
import com.example.todo.viewmodel.TodoCategoryViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class TodosActivity extends DaggerAppCompatActivity {

    @BindView(R.id.bottom_app_bar)
    BottomAppBar bottomAppBar;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.main_todo_frameLayout)
    FrameLayout frameLayout;
    @Inject
    TodoCategoryEntity initialTodoCategory;
    @Inject
    TodoCategoryViewModel todoCategoryViewModel;
    @Inject
    NavigationBottomSheetFragment navigationBottomSheetFragment;
    @Inject
    AddTodoBottomSheerFragment addTodoBottomSheerFragment;
    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);
        ButterKnife.bind(this);
        setSupportActionBar(bottomAppBar);

        if (sharedPreferences.getBoolean(SharedConstants.FIRST_START, true)) {
            todoCategoryViewModel.insertCategory(initialTodoCategory).observe(this, rowId -> {
                sharedPreferences.edit().putLong(SharedConstants.ACTIVE_CATEGORY, rowId).apply();
                sharedPreferences.edit().putBoolean(SharedConstants.FIRST_START, false).apply();
            });
        }


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTodoBottomSheerFragment.show(getSupportFragmentManager(),null);
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_todo_frameLayout,new TodosFragment());
        transaction.commit();

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
            navigationBottomSheetFragment.show(getSupportFragmentManager(),null);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
