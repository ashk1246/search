package com.white.whiterabbit.data

import com.white.whiterabbit.model.CompanyResponseModel

interface OperationCallback<T> {
    fun onError(error:String?)
    fun onDetailSuccess(data:List<T>?)
}