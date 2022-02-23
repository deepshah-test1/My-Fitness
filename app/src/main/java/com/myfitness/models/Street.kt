package com.myfitness.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Street(

    @SerializedName("number")
    val number : Int?,
    @SerializedName("name")
    val name : String?

) : Parcelable
