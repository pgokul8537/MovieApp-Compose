package com.example.movieapp.utils

object Constants {
    const val base_url: String = "https://api.themoviedb.org/3/"
    const val imageUrl: String = "https://image.tmdb.org/t/p/w500"
    const val originalImageUrl: String = "https://image.tmdb.org/t/p/original"
    const val access_token: String =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NWY5ZmYzMzhlNGY4MTc2Y2Q1OTM3MDZiODJmNTAxMSIsInN1YiI6IjVkYzk1ZmQ3NDcwZWFkMDAxMzk4N2ZhOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4q29og1uwwWqxFp63WK2W3qM8Wh0ZOq_N_waEmhkh2U"
    const val urlPopularMovies: String = "movie/popular"
    const val urlTopRatedMovies: String = "movie/top_rated"
    const val urlNowPlayingMovies: String = "movie/now_playing"
    const val urlUpcomingMovies: String = "movie/upcoming"
    var URL_MOVIE_DETAILS: String = "movie/%1\$s"
    var URL_SIMILAR_MOVIES: String = "movie/%1\$s/similar"
    var URL_MOVIE_IMAGES: String = "movie/%1\$s/images"
    const val URL_TRENDING_MOVIES: String = "trending/movie/week"

}