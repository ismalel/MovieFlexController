package com.ismael.movieflexcontroller.remote

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.ismael.movieflexpersistence.DetailData
import com.ismael.movieflexpersistence.entity.movie.MovieData
import com.ismael.movieflexpersistence.db.DataProccessor
import com.ismael.movieflexsecurity.encryption.Security

class Presenter(val context: Context):Callbacks.Presenter {
    var repository: Repository = Repository(this)
    var dataProcessor: DataProccessor = DataProccessor(context)
    var security: Security = Security(context)
    var gson: Gson = Gson()
    val api_key = "c8256dba313b52610fe8114f4a104ca2"
    override fun onGetData() {

        repository.getMoviePopular(api_key)
        repository.getMovieRated(api_key)
        repository.getMovieRecommendation(580489,api_key)
        repository.getTVPopular(api_key)
        repository.getTVRated(api_key)
        repository.getTVRecommendation(90462,api_key)
    }

    override fun onGetMoviePopular(content: MovieData) {
        val toEncryp: String? = gson.toJson(content)
        val toSave: String? = security.encrypt(toEncryp)
        dataProcessor.setStr("movie_popular", toSave)

    }

    override fun onGetMovieRated(content: MovieData) {
        val toEncryp: String? = gson.toJson(content)
        val toSave: String? = security.encrypt(toEncryp)
        dataProcessor.setStr("movie_rated", toSave)
    }

    override fun onGetMovieRecommendation(content: MovieData) {
        val toEncryp: String? = gson.toJson(content)
        val toSave: String? = security.encrypt(toEncryp)
        dataProcessor.setStr("movie_recommendation", toSave)
    }

    override fun onGetTVPopular(content: MovieData) {
        val toEncryp: String? = gson.toJson(content)
        val toSave: String? = security.encrypt(toEncryp)
        dataProcessor.setStr("tv_popular", toSave)
    }

    override fun onGetTVRated(content: MovieData) {
        val toEncryp: String? = gson.toJson(content)
        val toSave: String? = security.encrypt(toEncryp)
        dataProcessor.setStr("tv_rated", toSave)
    }

    override fun onGetTVRecommendation(content: MovieData) {
        val toEncryp: String? = gson.toJson(content)
        val toSave: String? = security.encrypt(toEncryp)
        dataProcessor.setStr("movie_popular", toSave)
    }

    override fun onFailure(failure: String) {
        Toast.makeText(context,failure,Toast.LENGTH_LONG).show()
    }

    override fun initItemData(item_id: Int, source: String?) {
        when(source){
            "movie"-> repository.getMovieDetail(item_id,api_key)
            "tv"-> repository.getTVDetail(item_id,api_key)
        }
    }

    override fun onGetContentList(content: DetailData) {
        val toEncryp: String? = gson.toJson(content)
        val toSave: String? = security.encrypt(toEncryp)
        dataProcessor.setStr("detail", toSave)
    }


}