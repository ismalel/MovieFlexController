package com.ismael.movieflexcontroller.main

import android.annotation.SuppressLint
import android.app.Activity
import com.ismael.movieflexapi.api.movie.repository.IMovieRepository
import com.ismael.movieflexapi.api.movie.repository.MovieRepository
import com.ismael.movieflexapi.api.tvshow.repository.ITVShowRepository
import com.ismael.movieflexapi.api.tvshow.repository.TVShowRepository

class MainInteractor(val activity: Activity, val presenter: MainContract.Presenter): MainContract.Interactor {
    val movieRepository : IMovieRepository = MovieRepository()
    val tvShowRepository: ITVShowRepository = TVShowRepository()

    @SuppressLint("CheckResult")
    override fun getMoviePopular(api_key: String?) {
        movieRepository.getPopular(api_key)
            ?.subscribe( { movieData ->
                presenter.onGetMoviePopular(movieData)
            }){throwable: Throwable ->
                presenter.onFailure(throwable.message.toString())
            }
    }
    @SuppressLint("CheckResult")
    override fun getMovieRated(api_key: String?) {
        movieRepository.getRated(api_key)
            ?.subscribe( { movieData ->
                presenter.onGetMovieRated(movieData)
            }){throwable: Throwable ->
                presenter.onFailure(throwable.message.toString())
            }
    }
    @SuppressLint("CheckResult")
    override fun getMovieRecommendation(movie_id: Int, api_key: String?) {
        movieRepository.getRecommendation(movie_id,api_key)
            ?.subscribe( { movieData ->
                presenter.onGetMovieRecommendation(movieData)
            }){throwable: Throwable ->
                presenter.onFailure(throwable.message.toString())
            }
    }
    @SuppressLint("CheckResult")
    override fun getTVPopular(api_key: String?) {
        tvShowRepository.getPopular(api_key)
            ?.subscribe( { movieData ->
                presenter.onGetTVPopular(movieData)
            }){throwable: Throwable ->
                presenter.onFailure(throwable.message.toString())
            }
    }
    @SuppressLint("CheckResult")
    override fun getTVRated(api_key: String?) {
        tvShowRepository.getRated(api_key)
            ?.subscribe( { movieData ->
                presenter.onGetTVRated(movieData)
            }){throwable: Throwable ->
                presenter.onFailure(throwable.message.toString())
            }
    }
    @SuppressLint("CheckResult")
    override fun getTVRecommendation(movie_id: Int, api_key: String?) {
        tvShowRepository.getRecommendation(movie_id,api_key)
            ?.subscribe( { movieData ->
                presenter.onGetTVRecommendation(movieData)
            }){throwable: Throwable ->
                presenter.onFailure(throwable.message.toString())
            }
    }
}