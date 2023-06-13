package com.example.movieapp.network.model

import android.os.Parcelable
import com.example.movieapp.utils.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreditResponse(
    @SerializedName("cast")
    val cast: List<Cast?>?,
    @SerializedName("crew")
    val crew: List<Crew?>?,
    @SerializedName("id")
    val id: Int?
) : Parcelable {
    @Parcelize
    data class Cast(
        @SerializedName("adult")
        val adult: Boolean?,
        @SerializedName("cast_id")
        val castId: Int?,
        @SerializedName("character")
        val character: String?,
        @SerializedName("credit_id")
        val creditId: String?,
        @SerializedName("gender")
        val gender: Int?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("known_for_department")
        val knownForDepartment: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("order")
        val order: Int?,
        @SerializedName("original_name")
        val originalName: String?,
        @SerializedName("popularity")
        val popularity: Double?,
        @SerializedName("profile_path")
        val profilePath: String?
    ) : Parcelable{
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

    @Parcelize
    data class Crew(
        @SerializedName("adult")
        val adult: Boolean?,
        @SerializedName("credit_id")
        val creditId: String?,
        @SerializedName("department")
        val department: String?,
        @SerializedName("gender")
        val gender: Int?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("job")
        val job: String?,
        @SerializedName("known_for_department")
        val knownForDepartment: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("original_name")
        val originalName: String?,
        @SerializedName("popularity")
        val popularity: Double?,
        @SerializedName("profile_path")
        val profilePath: String?
    ) : Parcelable
}