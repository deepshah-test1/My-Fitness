package com.myfitness.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Name(
    @SerializedName("title")
    val title : String?,
    @SerializedName("first")
    val first : String?,
    @SerializedName("last")
    val last : String?
) : Parcelable




