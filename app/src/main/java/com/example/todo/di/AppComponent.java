package com.example.todo.di;

import android.app.Application;

import com.example.todo.MainApplication;
import com.example.todo.login.LoginModule;
import com.example.todo.main.TodosModule;
import com.example.todo.splash.SplashModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBindingModule.class,
        ViewModelModule.class,
        LoginModule.class,
        TodosModule.class,
        SplashModule.class
})
public interface AppComponent extends AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
