package com.pafo37.askgemini.koin

import com.pafo37.askgemini.room.AppDatabase
import com.pafo37.askgemini.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // 1. Single instance of the Database
    single { AppDatabase.getDatabase(get()) }

    // 2. Single instance of the DAO (gets the DB from above)
    single { get<AppDatabase>().chatDao() }

    // 3. ViewModel factory (gets the DAO from above)
    viewModel { HomeViewModel(get()) }
}