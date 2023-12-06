package com.example.movieapp.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonImagesResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("profiles")
    val profiles: List<ImageItem>?
) : Parcelable