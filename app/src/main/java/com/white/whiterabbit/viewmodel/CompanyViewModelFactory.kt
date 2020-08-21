package com.white.whiterabbit.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.white.whiterabbit.model.CompanyDatasource

class CompanyViewModelFactory(private val repository: CompanyDatasource): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CompanyViewModel(repository,application = Application()) as T
    }
}