package com.example.todo.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.todo.viewmodel.TodoCategoryViewModel;
import com.example.todo.viewmodel.TodoViewModel;
import com.example.todo.viewmodel.TodoViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TodoCategoryViewModel.class)
    abstract ViewModel bindTodoCategoryViewModel(TodoCategoryViewModel todoCategoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TodoViewModel.class)
    abstract ViewModel bindTodoViewModel(TodoViewModel todoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(TodoViewModelFactory todoViewModelFactory);
}
