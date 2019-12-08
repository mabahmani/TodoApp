package com.example.todo.addcategory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;

import com.example.todo.R;
import com.example.todo.db.entity.TodoCategoryEntity;
import com.example.todo.util.SharedConstants;
import com.example.todo.viewmodel.TodoCategoryViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class AddCategoryActivity extends DaggerAppCompatActivity {

    @BindView(R.id.createNewListButton)
    MaterialButton createNewListButton;
    @BindView(R.id.createNewListEditText)
    AppCompatEditText createNewListEditText;
    @BindView(R.id.closeIcon)
    AppCompatImageView closeIcon;

    @Inject
    TodoCategoryViewModel todoCategoryViewModel;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        ButterKnife.bind(this);

        createNewListEditText.requestFocus();

        createNewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoCategoryViewModel.insertCategory(new TodoCategoryEntity(Objects.requireNonNull(createNewListEditText.getText()).toString())).observe(AddCategoryActivity.this, new Observer<Long>() {
                    @Override
                    public void onChanged(Long aLong) {
                        sharedPreferences.edit().putLong(SharedConstants.ACTIVE_CATEGORY, aLong).apply();
                        finish();
                    }
                });
            }
        });

        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
