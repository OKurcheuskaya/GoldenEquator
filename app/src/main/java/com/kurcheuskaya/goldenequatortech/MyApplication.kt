package com.kurcheuskaya.goldenequatortech

import android.app.Application
import com.kurcheuskaya.goldenequatortech.data.*
import com.kurcheuskaya.goldenequatortech.domain.MovieUseCase
import com.kurcheuskaya.goldenequatortech.presentation.MovieDetailsViewModel
import com.kurcheuskaya.goldenequatortech.presentation.MovieListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
    val appModule = module {
        // Define dependencies for the data layer
        single { MovieApiClient }
        single<MovieDataSource> { MovieDataSourceImpl(get()) }
        single<MovieRepository> { MovieRepositoryImpl(get()) }

        // Define dependencies for the domain layer
        single { MovieUseCase(get()) }

        // Define dependencies for the presentation layer
        viewModel { MovieListViewModel(get()) }
        viewModel { MovieDetailsViewModel(get()) }
    }
}