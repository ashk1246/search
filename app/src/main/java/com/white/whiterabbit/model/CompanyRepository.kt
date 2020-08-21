package com.white.whiterabbit.model

import com.white.whiterabbit.data.ApiClient
import com.white.whiterabbit.model.CompanyResponseModel
import com.white.whiterabbit.data.OperationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyRepository  : CompanyDatasource{

    private var call: Call<List<CompanyResponseModel>>?=null
    override fun retrieve(callback: OperationCallback<CompanyResponseModel>) {
        call= ApiClient.build()?.companyAPI()
        call?.enqueue(object : Callback<List<CompanyResponseModel>> {
            override fun onFailure(call: Call<List<CompanyResponseModel>>, t: Throwable) {
                callback.onError(t.message)
            }
            override fun onResponse(call: Call<List<CompanyResponseModel>>, response: Response<List<CompanyResponseModel>>) {
                response.body()?.let {
                    if(response.isSuccessful){
                        callback.onDetailSuccess(it)
                    }else{
                        callback.onError("Error")
                    }
                }
            }
        })
    }


}