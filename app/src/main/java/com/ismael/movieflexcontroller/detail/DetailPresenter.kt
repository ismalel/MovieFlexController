package com.ismael.movieflexcontroller.detail

import android.app.Activity
import com.ismael.movieflexpersistence.DetailData

class DetailPresenter(val activity: Activity, val view: DetailContract.View) : DetailContract.Presenter {
    var interactor: DetailContract.Interactor = DetailInteractor(activity,this)

    override fun onClickButton() {
        view.onClosedActivity()
    }

    override fun initItemData(item_id: Int, source: String?) {
        val api_key: String = "c8256dba313b52610fe8114f4a104ca2"
        when(source){
            "movie"-> interactor.getMovieDetail(item_id,api_key)
                "tv"-> interactor.getTVDetail(item_id,api_key)
        }
    }

    override fun onFailure(failure: String) {
        view.showMessage("Error on DetailPresenter " + failure)
    }

    override fun onGetContentList(content: DetailData) {
        view.onShowItemDetail(content)
    }

}