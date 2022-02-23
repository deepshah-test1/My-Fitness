package com.myfitness.viewmodel

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfitness.models.UserResponse
import com.myfitness.repository.MyFitnessRepository
import com.myfitness.repository.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.M)
class MyFitnessViewModel(application: Application) : AndroidViewModel(application) {

    val repository : MyFitnessRepository = MyFitnessRepository(application.applicationContext)
    val userLiveData : LiveData<Resource<UserResponse>>
    get() = repository.userLiveData

    init {
        getUserList(1,10)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getUserList(page : Int, results : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e("getUserListRequest",page.toString())
            repository.getUserList(page, results)
        }
    }
}