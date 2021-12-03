package com.ismael.movieflexcontroller.utils

import com.google.gson.Gson
import com.ismael.movieflexpersistence.MovieDetailData
import com.ismael.movieflexpersistence.entity.tv.detail.TVDetailData

class GsonHandler {
    val gson: Gson = Gson()

    fun movieDetailsToString(movieData: MovieDetailData): String?{
        return gson.toJson(movieData)
    }

    fun tvDetailsToString(tvData: TVDetailData): String?{
        return gson.toJson(tvData)
    }

    fun stringToMovieDetails(json: String?): MovieDetailData{
        return gson.fromJson(json,MovieDetailData::class.java)

    }

    fun stringToTVDetails(json: String?): TVDetailData{
        return gson.fromJson(json, TVDetailData::class.java)
    }
}