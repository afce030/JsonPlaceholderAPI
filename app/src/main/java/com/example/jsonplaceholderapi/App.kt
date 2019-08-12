package com.example.jsonplaceholderapi

import android.app.Application
import com.example.jsonplaceholderapi.di.DaggerComponents.DaggerRetrofitComponent
import com.example.jsonplaceholderapi.di.DaggerComponents.RetrofitComponent
import com.example.jsonplaceholderapi.di.DaggerModules.RetrofitModule

class App : Application() {

    lateinit var component: RetrofitComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerRetrofitComponent.builder().retrofitModule(RetrofitModule).build()
    }

    fun getRetrofitComponent() = component

}