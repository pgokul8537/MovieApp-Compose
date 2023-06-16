package com.example.movieapp

enum class NavigationRoute(var route: String) {
    MOVIES("movies"),
    TV_SHOWS("tv_shows"),
    EXPLORE("explore"),
    MOVIE_DETAILS("movie/details"),
    TV_SHOWS_DETAILS("tv/details"),
    MOVIES_VIEW_ALL("movie/view_all"),
    CREDITS_VIEW_ALL("credits/view_all"),
    PERSON_DETAILS("person/details"),
    SEARCH("search")
}