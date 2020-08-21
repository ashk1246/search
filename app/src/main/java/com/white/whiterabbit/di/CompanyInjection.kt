package com.white.whiterabbit.di

import androidx.lifecycle.ViewModelProvider
import com.white.whiterabbit.model.CompanyDatasource
import com.white.whiterabbit.model.CompanyRepository
import com.white.whiterabbit.viewmodel.CompanyViewModelFactory

object CompanyInjection {

    private val companyDatasource: CompanyDatasource =
        CompanyRepository()
    private val companyViewModelFactory =
        CompanyViewModelFactory(
            companyDatasource
        )

    fun providerRepository(): CompanyDatasource {
        return companyDatasource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory{
        return companyViewModelFactory
    }

}