package com.example.movieapp.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<MovieItem>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("dates")
    val dates: Dates?,
) : Parcelable {
    @Parcelize
    data class Dates(
        @SerializedName("maximum")
        val maximum: String?,
        @SerializedName("minimum")
        val minimum: String?
    ) : Parcelable
}