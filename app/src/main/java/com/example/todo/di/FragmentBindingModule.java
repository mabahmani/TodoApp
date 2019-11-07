package com.example.todo.di;

import com.example.todo.main.TodosFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector()
    abstract TodosFragment todosFragment();
}
