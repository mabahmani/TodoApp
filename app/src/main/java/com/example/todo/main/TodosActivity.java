package com.example.todo.main;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.example.todo.R;
import com.example.todo.db.entity.TodoCategoryEntity;
import com.example.todo.viewmodel.TodoCategoryViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TodosActivity extends DaggerAppCompatActivity {

    @BindView(R.id.bottom_app_bar)
    BottomAppBar bottomAppBar;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.main_todo_frameLayout)
    FrameLayout frameLayout;

    @Inject
    TodoCategoryViewModel todoCategoryViewModel;

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

        todoCategoryViewModel.getAllCategories().observe(this, new Observer<List<TodoCategoryEntity>>() {
            @Override
            public void onChanged(List<TodoCategoryEntity> todoCategoryEntities) {
                Log.d("AminSize", todoCategoryEntities.get(0).getCategoryName() + "");
            }
        });
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
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
