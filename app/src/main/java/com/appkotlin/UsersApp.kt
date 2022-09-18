package com.appkotlin

import android.app.Application
import com.appkotlin.di.dataBaseModule
import com.appkotlin.di.repositoryModule
import com.appkotlin.di.serviceAPIModule
import com.appkotlin.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UsersApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UsersApp)
            modules(listOf(
                viewModelModule,
                repositoryModule,
                serviceAPIModule,
                dataBaseModule
            ))
        }
    }
}