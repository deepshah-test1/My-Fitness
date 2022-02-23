package com.myfitness.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dob(

    @SerializedName("date")
    val date : String?,
    @SerializedName("age")
    val age : Int?

) : Parcelable

