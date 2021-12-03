package com.ismael.movieflexcontroller.remote

import com.ismael.movieflexpersistence.DetailData
import com.ismael.movieflexpersistence.entity.movie.MovieData

interface Callbacks {

    interface Repository{
        fun getMoviePopular(api_key: String?)
        fun getMovieRated(api_key: String?)
        fun getMovieRecommendation(movie_id : Int, api_key: String?)
        fun getTVPopular(api_key: String?)
        fun getTVRated(api_key: String?)
        fun getTVRecommendation(movie_id : Int, api_key: String?)
        fun getMovieDetail(item_id: Int, api_key : String?)
        fun getTVDetail(item_id: Int, api_key: String?)
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
        fun initItemData(item_id: Int, source: String?)
        fun onGetContentList(content: DetailData)

    }
}