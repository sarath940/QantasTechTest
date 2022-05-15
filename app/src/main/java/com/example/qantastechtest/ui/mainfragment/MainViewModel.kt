package com.example.qantastechtest.ui.mainfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qantastechtest.data.Resource
import com.example.qantastechtest.data.model.QantasDataModel
import com.example.qantastechtest.data.model.QantasDataModel.QantasDataModelItem
import com.example.qantastechtest.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(val repository: Repository) : ViewModel(),
    Repository by repository {

    val qantasDataModelLiveData: MutableLiveData<Resource<QantasDataModel>> =
        MutableLiveData()
    private var qantasDataModelResponseModel: QantasDataModel? = null


    fun getQantasDataModel() =
        viewModelScope.launch(Dispatchers.IO) {
            qantasDataModelResponseModel = null
            qantasDataModelLiveData.postValue(Resource.Loading())
            try {
                qantasDataModelLiveData.postValue(
                    handleGetQantasDataModelResponse(
                        repository.fetchRemoteData()
                    )
                )
            } catch (e: Exception) {
                qantasDataModelLiveData.postValue(Resource.Error("Error Occurred"))
            }
        }


    private fun handleGetQantasDataModelResponse(response: Response<QantasDataModel>): Resource<QantasDataModel> {
        try {
            if (response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    if (qantasDataModelResponseModel == null) {
                        qantasDataModelResponseModel = resultResponse
                    }
                    return Resource.Success(qantasDataModelResponseModel ?: resultResponse)
                }
            }
            return Resource.Error(response.message())
        } catch (ex: Exception) {
            return Resource.Error("Error Occurred")
        }
    }


    fun insertAll(list: List<QantasDataModelItem>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveData(list)
        }
    }

    fun getLocalData() = repository.fetchLocalData()
}