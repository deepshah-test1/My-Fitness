package com.myfitness.api

import com.myfitness.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyFitnessAPI {

    @GET("api/")
    suspend fun getUserList(@Query("page") page : Int,
                            @Query("results") results : Int) : Response<UserResponse>
}