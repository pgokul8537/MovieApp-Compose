package com.example.movieapp.network.model

import android.os.Parcelable
import com.example.movieapp.utils.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonDetailResponse(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("also_known_as")
    val alsoKnownAs: List<String?>?,
    @SerializedName("biography")
    val biography: String?,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("deathday")
    val deathday: String?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("place_of_birth")
    val placeOfBirth: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("profile_path")
    val profilePath: String?
) : Parcelable {
    fun getDefaultImagePath(): String? {
        if (!profilePath.isNullOrEmpty()) {
            return "${Constants.imageUrl}$profilePath"
        }
        return null
    }

    fun getOriginalImagePath(): String? {
        if (!profilePath.isNullOrEmpty()) {
            return "${Constants.originalImageUrl}$profilePath"
        }
        return null
    }
}