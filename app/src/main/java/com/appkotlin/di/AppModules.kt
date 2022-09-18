package com.appkotlin.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.appkotlin.models.Repository
import com.appkotlin.models.RepositoryImp
import com.appkotlin.models.local.LocalRepository
import com.appkotlin.models.local.LocalRepositoryImp
import com.appkotlin.models.local.UserDatabase
import com.appkotlin.mvvm.HomeActivityMVVM
import com.appkotlin.remote.RemoteRepository
import com.appkotlin.remote.RemoteRepositoryImp
import com.appkotlin.remote.ServiceAPI
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BaseURL:String="https://my-json-server.typicode.com/"
private const val DATABASE_NAME="user_database"

val viewModelModule:Module= module {
    viewModel{HomeActivityMVVM(repository = get())}
}

val repositoryModule= module {
    single<RemoteRepository> { RemoteRepositoryImp(api = get()) }
    single<LocalRepository> { LocalRepositoryImp(db = get()) }
    single<Repository> { RepositoryImp(remoteRepository = get(), localRepository = get()) }
}
val serviceAPIModule= module {
    fun getRetroBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { getRetroBuilder() }

    fun getServiceAPIInstance(retrofit: Retrofit):ServiceAPI{
        return retrofit.create(ServiceAPI::class.java)
    }
    single { getServiceAPIInstance(retrofit = get()) }
}

val dataBaseModule= module {
    fun getDatabaseInstance(application: Application): UserDatabase {
        return Room.databaseBuilder(
            application, UserDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
    single { getDatabaseInstance(androidApplication()) }
}