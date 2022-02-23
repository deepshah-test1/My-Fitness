package com.myfitness.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Id(

    @SerializedName("name")
    val name : String?,

    /** some problem related to datatype of this field in pojo class
     * it shows Object Datatype of this field*/
    @SerializedName("value")
    val value : String?

) : Parcelable
