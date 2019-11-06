package com.example.todo.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.example.todo.R;
import com.example.todo.db.TodoDb;
import com.example.todo.db.dao.TodoCategoryDao;
import com.example.todo.db.dao.TodoDao;
import com.example.todo.db.entity.TodoCategoryEntity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class AppModule {

    @Singleton
    @Provides
    TodoDb provideDb(Application application) {
        return Room.databaseBuilder(application, TodoDb.class, "todo.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    CalligraphyConfig provideCalligraphyConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSansMobile.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }


    @Singleton
    @Provides
    TodoCategoryDao provideTodoCategoryDao(TodoDb db) {
        return db.todoCategoryDao();
    }

    @Singleton
    @Provides
    TodoDao provideTodoDao(TodoDb db) {
        return db.todoDao();
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPrefrences(Application application) {
        return application.getSharedPreferences("AppPref", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    TodoCategoryEntity provideInitialTodoCategory() {
        return new TodoCategoryEntity("کارهای من");
    }
}
