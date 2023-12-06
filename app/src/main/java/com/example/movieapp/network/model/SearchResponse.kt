package com.example.movieapp.network.model

import android.os.Parcelable
import com.example.movieapp.utils.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<SearchItem>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
) : Parcelable {
    @Parcelize
    data class SearchItem(
        @SerializedName("adult") val adult: Boolean?,
        @SerializedName("backdrop_path") val backdropPath: String?,
        @SerializedName("gender") val gender: Int?,
        @SerializedName("genre_ids") val genreIds: List<Int?>?,
        @SerializedName("id") val id: Int?,
        @SerializedName("known_for") val knownFor: List<KnownFor?>?,
        @SerializedName("known_for_department") val knownForDepartment: String?,
        @SerializedName("media_type") val mediaType: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("original_language") val originalLanguage: String?,
        @SerializedName("original_name") val originalName: String?,
        @SerializedName("original_title") val originalTitle: String?,
        @SerializedName("overview") val overview: String?,
        @SerializedName("popularity") val popularity: Double?,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("profile_path") val profilePath: String?,
        @SerializedName("release_date") val releaseDate: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("video") val video: Boolean?,
        @SerializedName("vote_average") val voteAverage: Double?,
        @SerializedName("vote_count") val voteCount: Int?
    ) : Parcelable {

        fun getDefaultImagePath(): String? {
            if (!profilePath.isNullOrEmpty()) {
                return "${Constants.imageUrl}$profilePath"
            } else if (!posterPath.isNullOrEmpty()) {
                return "${Constants.imageUrl}$posterPath"
            }
            return null
        }

        @Parcelize
        data class KnownFor(
            @SerializedName("adult") val adult: Boolean?,
            @SerializedName("backdrop_path") val backdropPath: String?,
            @SerializedName("first_air_date") val firstAirDate: String?,
            @SerializedName("genre_ids") val genreIds: List<Int?>?,
            @SerializedName("id") val id: Int?,
            @SerializedName("media_type") val mediaType: String?,
            @SerializedName("name") val name: String?,
            @SerializedName("origin_country") val originCountry: List<String?>?,
            @SerializedName("original_language") val originalLanguage: String?,
            @SerializedName("original_name") val originalName: String?,
            @SerializedName("original_title") val originalTitle: String?,
            @SerializedName("overview") val overview: String?,
            @SerializedName("popularity") val popularity: Double?,
            @SerializedName("poster_path") val posterPath: String?,
            @SerializedName("release_date") val releaseDate: String?,
            @SerializedName("title") val title: String?,
            @SerializedName("video") val video: Boolean?,
            @SerializedName("vote_average") val voteAverage: Double?,
            @SerializedName("vote_count") val voteCount: Int?
        ) : Parcelable
    }
}