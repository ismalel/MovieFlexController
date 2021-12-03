package com.ismael.movieflexcontroller.main

import android.app.Activity
import android.graphics.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ismael.movieflexbussinesslogic.network.CheckNetwork
import com.ismael.movieflexpersistence.db.DataProccessor
import com.ismael.movieflexpersistence.entity.movie.MovieData
import com.ismael.movieflexpersistence.entity.movie.Result
import com.ismael.movieflexsecurity.encryption.Security
import java.lang.reflect.Type

class MainPresenter(val activity: Activity, val view: MainContract.View):MainContract.Presenter {
    val interactor: MainContract.Interactor = MainInteractor(activity,this)
    var dataProcessor: DataProccessor = DataProccessor(activity)
    var security: Security = Security(activity)
    var checkNetwork: CheckNetwork = CheckNetwork(activity)
    var gson: Gson = Gson()

    override fun onGetData() {
        val api_key = "c8256dba313b52610fe8114f4a104ca2"

        if(checkNetwork.isConnected()){
            interactor.getMoviePopular(api_key)
            interactor.getMovieRated(api_key)
            interactor.getMovieRecommendation(580489,api_key)
            interactor.getTVPopular(api_key)
            interactor.getTVRated(api_key)
            interactor.getTVRecommendation(90462,api_key)
        }else{
            onGetMoviePopularOffline()
            onGetMovieRatedOffline()
            onGetMovieRecommendationOffline()
            onGetTVPopularOffline()
            onGetTVRatedOffline()
            onGetTVRecommendationOffline()
            view.connectionStatus(false)
        }

    }

    override fun onGetMoviePopular(content: MovieData) {
        val results:ArrayList<Result> = content.results as ArrayList<Result>
        saveToSharedPreferences(content,"movie_popular")
        view.showMoviePopularList(results)
    }

    override fun onGetMovieRated(content: MovieData) {
        val results:ArrayList<Result> = content.results as ArrayList<Result>
        saveToSharedPreferences(content,"movie_rated")
        view.showMovieRatedList(results)
    }

    override fun onGetMovieRecommendation(content: MovieData) {
        val results:ArrayList<Result> = content.results as ArrayList<Result>
        saveToSharedPreferences(content,"movie_recommendation")
        view.showMovieRecommendedList(results)
    }

    override fun onGetTVPopular(content: MovieData) {
        val results:ArrayList<Result> = content.results as ArrayList<Result>
        saveToSharedPreferences(content,"tv_popular")
        view.showTVPopularList(results)
    }

    override fun onGetTVRated(content: MovieData) {
        val results:ArrayList<Result> = content.results as ArrayList<Result>
        saveToSharedPreferences(content,"tv_rated")
        view.showTVRatedList(results)
    }

    override fun onGetTVRecommendation(content: MovieData) {
        val results:ArrayList<Result> = content.results as ArrayList<Result>
        saveToSharedPreferences(content,"tv_recommendation")
        view.showTVRecommendedList(results)
    }


    override fun onFailure(failure: String) {
        view.showMessage("Error on MainPresenter: $failure")
    }

    override fun onGetMoviePopularOffline() {
        view.showMoviePopularListOffline(getFromSharedPreferences("movie_popular"))
    }

    override fun onGetMovieRatedOffline() {
        view.showMovieRatedListOffline(getFromSharedPreferences("movie_rated"))
    }

    override fun onGetMovieRecommendationOffline() {
        view.showMovieRecommendedListOffline(getFromSharedPreferences("movie_recommendation"))
    }

    override fun onGetTVPopularOffline() {
        view.showTVPopularListOffline(getFromSharedPreferences("tv_popular"))
    }

    override fun onGetTVRatedOffline() {
        view.showTVRatedListOffline(getFromSharedPreferences("tv_rated"))
    }

    override fun onGetTVRecommendationOffline() {
        view.showTVRecommendedListOffline(getFromSharedPreferences("tv_recommendation"))
    }

    fun saveToSharedPreferences(content:MovieData, key: String){
        val toEncryp: String? = gson.toJson(content)
        val toSave: String? = security.encrypt(toEncryp)
        dataProcessor.setStr(key, toSave)
    }
    fun getFromSharedPreferences(key: String?) : ArrayList<Result>{
        var movieData : MovieData? = null
        val toDecryp = dataProcessor.getStr(key)
        val toRetrieve = security.decrypt(toDecryp)
        val type = object : TypeToken<MovieData?>() {}.type
        movieData = gson.fromJson(toRetrieve,type)
        val results:ArrayList<Result> = movieData!!.results as ArrayList<Result>
        return results
    }
}