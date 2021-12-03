package com.ismael.movieflexcontroller.main

import android.app.Activity
import com.ismael.movieflexpersistence.entity.movie.MovieData
import com.ismael.movieflexpersistence.entity.movie.Result

class MainPresenter(val activity: Activity, val view: MainContract.View):MainContract.Presenter {
    val interactor: MainContract.Interactor = MainInteractor(activity,this)

    override fun onGetData() {
        val api_key = "c8256dba313b52610fe8114f4a104ca2"
        interactor.getMoviePopular(api_key)
        interactor.getMovieRated(api_key)
        interactor.getMovieRecommendation(580489,api_key)
        interactor.getTVPopular(api_key)
        interactor.getTVRated(api_key)
        interactor.getTVRecommendation(90462,api_key)
    }

    override fun onGetMoviePopular(content: MovieData) {
        val results:ArrayList<Result> = content.results as ArrayList<Result>
        view.showMoviePopularList(results)
    }

    override fun onGetMovieRated(content: MovieData) {
        val results:ArrayList<Result> = content.results as ArrayList<Result>
        view.showMovieRatedList(results)
    }

    override fun onGetMovieRecommendation(content: MovieData) {
        val results:ArrayList<Result> = content.results as ArrayList<Result>
        view.showMovieRecommendedList(results)
    }

    override fun onGetTVPopular(content: MovieData) {
        val results:ArrayList<Result> = content.results as ArrayList<Result>
        view.showTVPopularList(results)
    }

    override fun onGetTVRated(content: MovieData) {
        val results:ArrayList<Result> = content.results as ArrayList<Result>
        view.showTVRatedList(results)
    }

    override fun onGetTVRecommendation(content: MovieData) {
        val results:ArrayList<Result> = content.results as ArrayList<Result>
        view.showTVRecommendedList(results)
    }


    override fun onFailure(failure: String) {
        view.showMessage("Error on MainPresenter: $failure")
    }
}