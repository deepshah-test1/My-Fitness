package com.myfitness.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.myfitness.api.RetrofitInstance
import com.myfitness.models.UserResponse
import com.myfitness.util.NetworkUtils
import retrofit2.Response

class MyFitnessRepository(private val applicationContext : Context) {

    /*
    suspend fun getUserList(page : Int, results : Int) =
        RetrofitInstance.api.getUserList(page, results)

     */

    val userMutableLiveData = MutableLiveData<Resource<UserResponse>>()

    val userLiveData : LiveData<Resource<UserResponse>>
    get() = userMutableLiveData


    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getUserList(page : Int, results : Int) {

        if(NetworkUtils.isOnline(applicationContext)){
            try {
                val result = RetrofitInstance.api.getUserList(page, results)
                if(result?.body() != null){
                    userMutableLiveData.postValue(Resource.Success(result.body()))
                }
            }

            catch(e : Exception){
                userMutableLiveData.postValue(Resource.Error(e.message.toString()))

            }
        }
    }
}