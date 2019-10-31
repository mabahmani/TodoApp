package com.example.todo.addcategory;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import com.example.todo.R;
import com.example.todo.db.entity.TodoCategoryEntity;
import com.example.todo.viewmodel.TodoCategoryViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddCategoryActivity extends DaggerAppCompatActivity {

    @BindView(R.id.createNewListButton)
    MaterialButton createNewListButton;
    @BindView(R.id.createNewListEditText)
    AppCompatEditText createNewListEditText;

    @Inject
    TodoCategoryViewModel todoCategoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        ButterKnife.bind(this);
        createNewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoCategoryViewModel.insertCategory(new TodoCategoryEntity(Objects.requireNonNull(createNewListEditText.getText()).toString()));
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
