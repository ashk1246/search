package com.white.whiterabbit.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.white.whiterabbit.data.OperationCallback
import com.white.whiterabbit.model.CompanyDatasource
import com.white.whiterabbit.model.CompanyResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompanyViewModel (private val repository: CompanyDatasource,application: Application): AndroidViewModel(application) {


    private val _search = MutableLiveData<List<CompanyResponseModel>>().apply { value = emptyList() }
    val searchs: LiveData<List<CompanyResponseModel>> = _search

    private val _isViewLoading= MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError= MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList= MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList





    fun loadSearch() {
        _isViewLoading.postValue(true)
        repository.retrieve(object:
            OperationCallback<CompanyResponseModel> {
            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue( error)
            }


            override fun onDetailSuccess(data: List<CompanyResponseModel>?) {

                _isViewLoading.postValue(false)

                if(data!=null){
                    if(data.isEmpty()){
                        _isEmptyList.postValue(true)
                    }else{
                        _search.value= data
                    }
                }
            }


        })
    }



}