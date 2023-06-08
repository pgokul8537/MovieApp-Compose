package com.example.movieapp

sealed class BottomNavItem(var title: String, var icon: Int, var route: String) {
    object Movies : BottomNavItem("Movies", R.drawable.ic_movie, NavigationRoute.MOVIES.route)
    object TvShows :
        BottomNavItem("TV Shows", R.drawable.ic_tv_show, NavigationRoute.TV_SHOWS.route)

    object Explore : BottomNavItem("Explore", R.drawable.ic_explore, NavigationRoute.EXPLORE.route)
}
