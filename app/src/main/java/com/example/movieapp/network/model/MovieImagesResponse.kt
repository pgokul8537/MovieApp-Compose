package com.example.movieapp.network.model

import android.os.Parcelable
import com.example.movieapp.utils.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieImagesResponse(
    @SerializedName("backdrops")
    val backdrops: List<MovieImage>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logos")
    val logos: List<MovieImage>?,
    @SerializedName("posters")
    val posters: List<MovieImage>?
) : Parcelable {
    @Parcelize
    data class MovieImage(
        @SerializedName("aspect_ratio")
        val aspectRatio: Double?,
        @SerializedName("file_path")
        val filePath: String?,
        @SerializedName("height")
        val height: Int?,
        @SerializedName("iso_639_1")
        val iso6391: String?,
        @SerializedName("vote_average")
        val voteAverage: Double?,
        @SerializedName("vote_count")
        val voteCount: Int?,
        @SerializedName("width")
        val width: Int?
    ) : Parcelable {
        fun getDefaultImagePath(): String? {
            if (!filePath.isNullOrEmpty()) {
                return "${Constants.imageUrl}$filePath"
            }
            return null
        }

        fun getOriginalImagePath(): String? {
            if (!filePath.isNullOrEmpty()) {
                return "${Constants.originalImageUrl}$filePath"
            }
            return null
        }
    }
}