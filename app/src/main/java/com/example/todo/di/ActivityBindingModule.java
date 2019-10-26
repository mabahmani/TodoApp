package com.example.todo.di;

import com.example.todo.login.LoginActivity;
import com.example.todo.login.LoginModule;
import com.example.todo.main.TodosActivity;
import com.example.todo.main.TodosModule;
import com.example.todo.splash.SplashActivity;
import com.example.todo.splash.SplashModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract SplashActivity splashActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = TodosModule.class)
    abstract TodosActivity todosActivity();

}
