package com.ismael.movieflexcontroller.main

import android.app.Activity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ismael.movieflexbussinesslogic.network.CheckNetwork
import com.ismael.movieflexpersistence.db.DataProccessor
import com.ismael.movieflexpersistence.entity.movie.MovieData
import com.ismael.movieflexpersistence.entity.movie.MovieResult
import com.ismael.movieflexpersistence.entity.tv.TVData
import com.ismael.movieflexpersistence.entity.tv.TVResult
import com.ismael.movieflexsecurity.encryption.Security

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
        val results:ArrayList<MovieResult> = content.results
        saveMovieToSharedPreferences(content,"movie_popular")
        view.showMoviePopularList(results)
    }

    override fun onGetMovieRated(content: MovieData) {
        val results:ArrayList<MovieResult> = content.results
        saveMovieToSharedPreferences(content,"movie_rated")
        view.showMovieRatedList(results)
    }

    override fun onGetMovieRecommendation(content: MovieData) {
        val results:ArrayList<MovieResult> = content.results
        saveMovieToSharedPreferences(content,"movie_recommendation")
        view.showMovieRecommendedList(results)
    }

    override fun onGetTVPopular(content: TVData) {
        val results:ArrayList<TVResult> = content.results
        saveTVToSharedPreferences(content,"tv_popular")
        view.showTVPopularList(results)
    }

    override fun onGetTVRated(content: TVData) {
        val results:ArrayList<TVResult> = content.results
        saveTVToSharedPreferences(content,"tv_rated")
        view.showTVRatedList(results)
    }

    override fun onGetTVRecommendation(content: TVData) {
        val results:ArrayList<TVResult> = content.results
        saveTVToSharedPreferences(content,"tv_recommendation")
        view.showTVRecommendedList(results)
    }


    override fun onFailure(failure: String) {
        view.showMessage("Error on MainPresenter: $failure")
    }

    override fun onGetMoviePopularOffline() {
        view.showMoviePopularListOffline(getMovieFromSharedPreferences("movie_popular"))
    }

    override fun onGetMovieRatedOffline() {
        view.showMovieRatedListOffline(getMovieFromSharedPreferences("movie_rated"))
    }

    override fun onGetMovieRecommendationOffline() {
        view.showMovieRecommendedListOffline(getMovieFromSharedPreferences("movie_recommendation"))
    }

    override fun onGetTVPopularOffline() {
        view.showTVPopularListOffline(getTVFromSharedPreferences("tv_popular"))
    }

    override fun onGetTVRatedOffline() {
        view.showTVRatedListOffline(getTVFromSharedPreferences("tv_rated"))
    }

    override fun onGetTVRecommendationOffline() {
        view.showTVRecommendedListOffline(getTVFromSharedPreferences("tv_recommendation"))
    }

    fun saveMovieToSharedPreferences(content:MovieData, key: String){
        val toEncryp: String? = gson.toJson(content)
        val toSave: String? = security.encrypt(toEncryp)
        dataProcessor.setStr(key, toSave)
    }

    fun saveTVToSharedPreferences(content:TVData, key: String){
        val toEncryp: String? = gson.toJson(content)
        val toSave: String? = security.encrypt(toEncryp)
        dataProcessor.setStr(key, toSave)
    }
    fun getMovieFromSharedPreferences(key: String?) : ArrayList<MovieResult>{
        var movieData : MovieData? = null
        val toDecryp = dataProcessor.getStr(key)
        val toRetrieve = security.decrypt(toDecryp)
        val type = object : TypeToken<MovieData?>() {}.type
        movieData = gson.fromJson(toRetrieve,type)
        val results:ArrayList<MovieResult> = movieData!!.results
        return results
    }

    fun getTVFromSharedPreferences(key: String?) : ArrayList<TVResult>{
        var tvData : TVData? = null
        val toDecryp = dataProcessor.getStr(key)
        val toRetrieve = security.decrypt(toDecryp)
        val type = object : TypeToken<TVData?>() {}.type
        tvData = gson.fromJson(toRetrieve,type)
        val results:ArrayList<TVResult> = tvData!!.results
        return results
    }
}