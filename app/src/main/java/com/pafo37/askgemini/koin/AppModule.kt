package com.pafo37.askgemini.koin

import com.pafo37.askgemini.room.AppDatabase
import com.pafo37.askgemini.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(get()) }

    single { get<AppDatabase>().chatDao() }

    viewModel { HomeViewModel(get()) }
}