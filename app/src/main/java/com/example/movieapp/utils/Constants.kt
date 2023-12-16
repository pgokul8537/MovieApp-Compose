package com.example.movieapp.utils

object Constants {
    const val base_url: String = "https://api.themoviedb.org/3/"
    const val imageUrl: String = "https://image.tmdb.org/t/p/w500"
    const val originalImageUrl: String = "https://image.tmdb.org/t/p/original"
    const val access_token: String =
        "Bearer Token "

    //    Movies
    const val URL_POPULAR_MOVIES: String = "movie/popular"
    const val URL_TOP_RATED_MOVIES: String = "movie/top_rated"
    const val URL_NOW_PLAYING_MOVIES: String = "movie/now_playing"
    const val URL_UPCOMING_MOVIES: String = "movie/upcoming"
    const val URL_TRENDING_MOVIES: String = "trending/movie/day"
    var URL_MOVIE_DETAILS: String = "movie/%1\$s"
    var URL_TV_DETAILS: String = "tv/%1\$s"
    var URL_SIMILAR_MOVIES: String = "movie/%1\$s/similar"
    var URL_SIMILAR_TV: String = "tv/%1\$s/similar"
    var URL_CREDITS_MOVIES: String = "movie/%1\$s/credits"
    var URL_CREDITS_TV: String = "tv/%1\$s/credits"
    var URL_MOVIE_IMAGES: String = "movie/%1\$s/images"
    var URL_TV_IMAGES: String = "tv/%1\$s/images"

    //    Person
    const val URL_UPCOMING_PERSON: String = "person/popular"
    const val URL_TRENDING_PERSON: String = "trending/person/day"
    var URL_PERSON_DETAILS: String = "person/%1\$s"
    var URL_PERSON_IMAGES: String = "person/%1\$s/images"
    var URL_PERSON_CREDITS_MOVIES: String = "person/%1\$s/movie_credits"
    var URL_PERSON_MOVIES: String =
        "/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&with_people=%1\$s"

    //    Search
    const val URL_SEARCH_MULTI: String = "search/multi"
    const val URL_SEARCH_MOVIE: String = "search/movie"
    const val URL_SEARCH_PERSON: String = "search/person"
    const val URL_SEARCH_TV: String = "search/tv"

    //    Discover
    const val URL_DISCOVER_MOVIE: String = "discover/movie"
    const val URL_DISCOVER_TV: String = "discover/tv"

    //Tv Show
    const val URL_AIRING_TODAY_TV: String = "tv/airing_today"
    const val URL_ON_THE_AIR_TV: String = "tv/on_the_air"
    const val URL_TOP_RATED_TV: String = "tv/top_rated"
    const val URL_POPULAR_TV: String = "tv/popular"
    const val URL_TRENDING_TV: String = "trending/tv/day"


}