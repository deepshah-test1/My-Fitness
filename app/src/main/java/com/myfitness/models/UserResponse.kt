package com.myfitness.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserResponse(

    @SerializedName("results")
    val results : List<Result>?,
    @SerializedName("info")
    val info : Info?
) : Parcelable
