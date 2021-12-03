package com.ismael.movieflexcontroller.main

import com.ismael.movieflexpersistence.entity.movie.MovieData
import com.ismael.movieflexpersistence.entity.movie.Result

interface MainContract {

    interface View{
        fun showMoviePopularList(content: ArrayList<Result>)
        fun showMovieRatedList(content: ArrayList<Result>)
        fun showMovieRecommendedList(content: ArrayList<Result>)
        fun showTVPopularList(content: ArrayList<Result>)
        fun showTVRatedList(content: ArrayList<Result>)
        fun showTVRecommendedList(content: ArrayList<Result>)
        fun showMessage(message: String)
        fun showMoviePopularListOffline(content: ArrayList<Result>)
        fun showMovieRatedListOffline(content: ArrayList<Result>)
        fun showMovieRecommendedListOffline(content: ArrayList<Result>)
        fun showTVPopularListOffline(content: ArrayList<Result>)
        fun showTVRatedListOffline(content: ArrayList<Result>)
        fun showTVRecommendedListOffline(content: ArrayList<Result>)
        fun connectionStatus(status: Boolean)

    }

    interface Presenter{
        fun onGetData()
        fun onGetMoviePopular(content: MovieData)
        fun onGetMovieRated(content: MovieData)
        fun onGetMovieRecommendation(content: MovieData)
        fun onGetTVPopular(content: MovieData)
        fun onGetTVRated(content: MovieData)
        fun onGetTVRecommendation(content: MovieData)
        fun onFailure(failure: String)
        fun onGetMoviePopularOffline()
        fun onGetMovieRatedOffline()
        fun onGetMovieRecommendationOffline()
        fun onGetTVPopularOffline()
        fun onGetTVRatedOffline()
        fun onGetTVRecommendationOffline()
    }

    interface Interactor{
        fun getMoviePopular(api_key: String?)
        fun getMovieRated(api_key: String?)
        fun getMovieRecommendation(movie_id : Int, api_key: String?)
        fun getTVPopular(api_key: String?)
        fun getTVRated(api_key: String?)
        fun getTVRecommendation(movie_id : Int, api_key: String?)
    }
}