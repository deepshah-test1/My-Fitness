package com.myfitness.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Timezone(

    @SerializedName("offset")
    val offset : String?,
    @SerializedName("description")
    val description : String?

) : Parcelable
