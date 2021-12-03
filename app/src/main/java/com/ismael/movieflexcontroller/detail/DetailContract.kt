package com.ismael.movieflexcontroller.detail

import com.ismael.movieflexpersistence.DetailData

interface DetailContract {

    interface View{
        fun onClosedActivity()
        fun onShowItemDetail(content: DetailData)
        fun showMessage(message: String?)
    }

    interface Presenter{
        fun onClickButton()
        fun initItemData(item_id: Int, source: String?)
        fun onFailure(failure: String)
        fun onGetContentList(content: DetailData)
    }

    interface Interactor{
        fun getMovieDetail(item_id: Int, api_key : String?)
        fun getTVDetail(item_id: Int, api_key: String?)
    }
}