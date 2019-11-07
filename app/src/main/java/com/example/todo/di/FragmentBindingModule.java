package com.example.todo.di;

import com.example.todo.main.TodosFragment;
import com.example.todo.main.TodosFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = TodosFragmentModule.class)
    abstract TodosFragment todosFragment();
}
