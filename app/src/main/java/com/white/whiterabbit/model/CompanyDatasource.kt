package com.white.whiterabbit.model

import com.white.whiterabbit.data.CompanyResponse
import com.white.whiterabbit.data.OperationCallback

interface CompanyDatasource {

    fun retrieve(callback: OperationCallback<CompanyResponseModel>)

}