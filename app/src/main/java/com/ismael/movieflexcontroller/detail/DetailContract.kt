package com.ismael.movieflexcontroller.detail

import com.ismael.movieflexpersistence.MovieDetailData
import com.ismael.movieflexpersistence.entity.tv.detail.TVDetailData


interface DetailContract {

    interface View{
        fun onClosedActivity()
        fun onShowMovieDetail(content: MovieDetailData)
        fun onShowTVDetail(content: TVDetailData)
        fun showMessage(message: String?)
    }

    interface Presenter{
        fun onClickButton()
        fun initItemData(item_id: Int, source: String?)
        fun onFailure(failure: String)
        fun onGetMovieList(content: MovieDetailData)
        fun onGetTVList(content: TVDetailData)
    }

    interface Interactor{
        fun getMovieDetail(item_id: Int, api_key : String?)
        fun getTVDetail(item_id: Int, api_key: String?)
    }
}