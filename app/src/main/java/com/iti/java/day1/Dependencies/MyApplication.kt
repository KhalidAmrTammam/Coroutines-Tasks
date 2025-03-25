package com.iti.java.day1.Dependencies

import android.app.Application
import com.iti.java.day1.AppContainer

class MyApplication : Application() {
    //lateinit var serviceLocator: ServiceLocator
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
       // serviceLocator = ServiceLocatorImpl(this)
        appContainer = AppContainer(this)
    }
}
