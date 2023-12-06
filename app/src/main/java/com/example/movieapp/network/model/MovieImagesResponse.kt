package com.example.movieapp.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieImagesResponse(
    @SerializedName("backdrops")
    val backdrops: List<ImageItem>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logos")
    val logos: List<ImageItem>?,
    @SerializedName("posters")
    val posters: List<ImageItem>?
) : Parcelable