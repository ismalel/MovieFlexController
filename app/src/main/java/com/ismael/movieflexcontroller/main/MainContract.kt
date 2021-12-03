package com.ismael.movieflexcontroller.main

import com.ismael.movieflexpersistence.entity.movie.MovieData
import com.ismael.movieflexpersistence.entity.movie.MovieResult
import com.ismael.movieflexpersistence.entity.tv.TVData
import com.ismael.movieflexpersistence.entity.tv.TVResult

interface MainContract {

    interface View{
        fun showMoviePopularList(content: ArrayList<MovieResult>)
        fun showMovieRatedList(content: ArrayList<MovieResult>)
        fun showMovieRecommendedList(content: ArrayList<MovieResult>)
        fun showTVPopularList(content: ArrayList<TVResult>)
        fun showTVRatedList(content: ArrayList<TVResult>)
        fun showTVRecommendedList(content: ArrayList<TVResult>)
        fun showMessage(message: String)
        fun showMoviePopularListOffline(content: ArrayList<MovieResult>)
        fun showMovieRatedListOffline(content: ArrayList<MovieResult>)
        fun showMovieRecommendedListOffline(content: ArrayList<MovieResult>)
        fun showTVPopularListOffline(content: ArrayList<TVResult>)
        fun showTVRatedListOffline(content: ArrayList<TVResult>)
        fun showTVRecommendedListOffline(content: ArrayList<TVResult>)
        fun connectionStatus(status: Boolean)

    }

    interface Presenter{
        fun onGetData()
        fun onGetMoviePopular(content: MovieData)
        fun onGetMovieRated(content: MovieData)
        fun onGetMovieRecommendation(content: MovieData)
        fun onGetTVPopular(content: TVData)
        fun onGetTVRated(content: TVData)
        fun onGetTVRecommendation(content: TVData)
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