package com.example.movieapp.utils

object Constants {
    const val base_url: String = "https://api.themoviedb.org/3/"
    const val imageUrl: String = "https://image.tmdb.org/t/p/w500"
    const val originalImageUrl: String = "https://image.tmdb.org/t/p/original"
    const val access_token: String =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NWY5ZmYzMzhlNGY4MTc2Y2Q1OTM3MDZiODJmNTAxMSIsInN1YiI6IjVkYzk1ZmQ3NDcwZWFkMDAxMzk4N2ZhOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4q29og1uwwWqxFp63WK2W3qM8Wh0ZOq_N_waEmhkh2U"
    const val URL_POPULAR_MOVIES: String = "movie/popular"
    const val URL_TOP_RATED_MOVIES: String = "movie/top_rated"
    const val URL_NOW_PLAYING_MOVIES: String = "movie/now_playing"
    const val URL_UPCOMING_MOVIES: String = "movie/upcoming"
    var URL_MOVIE_DETAILS: String = "movie/%1\$s"
    var URL_PERSON_DETAILS: String = "person/%1\$s"
    var URL_SIMILAR_MOVIES: String = "movie/%1\$s/similar"
    var URL_CREDITS_MOVIES: String = "movie/%1\$s/credits"
    var URL_MOVIE_IMAGES: String = "movie/%1\$s/images"
    var URL_PERSON_IMAGES: String = "person/%1\$s/images"
    var URL_PERSON_CREDITS_MOVIES: String = "person/%1\$s/movie_credits"
    var URL_PERSON_MOVIES: String =
        "/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&with_people=%1\$s"
    const val URL_TRENDING_MOVIES: String = "trending/movie/day"

}