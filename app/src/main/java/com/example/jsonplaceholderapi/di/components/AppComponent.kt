package com.example.jsonplaceholderapi.di.components

import com.example.jsonplaceholderapi.App
import com.example.jsonplaceholderapi.di.modules.ActivityInjectorsModule
import com.example.jsonplaceholderapi.di.modules.FragmentInjectorsModule
import com.example.jsonplaceholderapi.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import kotlin.text.Typography.dagger

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityInjectorsModule::class,
        FragmentInjectorsModule::class,
        AppModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}