package com.extremeprogramming.financetracker.ui.tools

import android.app.Application
import com.extremeprogramming.financetracker.db.AppRepository
import com.extremeprogramming.financetracker.ui.factory.HomeViewModelFactory
import com.extremeprogramming.financetracker.ui.factory.ReportsViewModelFactory

object InjectorUtils {
    fun provideHomeViewModelFactory(application: Application): HomeViewModelFactory {
        return HomeViewModelFactory(application, AppRepository.getRepository(application))
    }
    fun provideReportsViewModelFactory(application: Application): ReportsViewModelFactory {
        return ReportsViewModelFactory(application, AppRepository.getRepository(application))
    }
}