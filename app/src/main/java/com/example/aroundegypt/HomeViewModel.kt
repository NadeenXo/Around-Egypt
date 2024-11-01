package com.example.aroundegypt

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.experience_response.ExperiencesResponse
import com.example.aroundegypt.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExperienceViewModel(private var service: ApiService) : ViewModel() {
    private val _experiences: MutableLiveData<ExperiencesResponse> = MutableLiveData()
    val experiences: LiveData<ExperiencesResponse> = _experiences

    private val _recent: MutableLiveData<ExperiencesResponse> = MutableLiveData()
    val recent: LiveData<ExperiencesResponse> = _recent

    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message

    init {
        getExperiencedData()
    }

    fun getExperiencedData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.getRecommendedExperiences()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        _experiences.postValue(response.body())
                        Log.i("HomeFragment", "getData: ${response.body()}")
                        Log.d("HomeFragment", "Data fetched: ${response.body()?.data}")

                    } else {
                        _message.postValue("Failed to load data: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _message.postValue("Error: ${e.message}")
                }
            }
        }
    }
    fun getRecentData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.getRecentExperiences()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        _recent.postValue(response.body())

                    } else {
                        _message.postValue("Failed to load data: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _message.postValue("Error: ${e.message}")
                }
            }
        }
    }

}

class ExperienceFactory(private var service: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExperienceViewModel::class.java)) {
            return ExperienceViewModel(service) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}
