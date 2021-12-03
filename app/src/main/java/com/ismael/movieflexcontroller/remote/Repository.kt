package com.ismael.movieflexcontroller.remote

import android.annotation.SuppressLint
import com.ismael.movieflexapi.api.movie.repository.IMovieRepository
import com.ismael.movieflexapi.api.movie.repository.MovieRepository
import com.ismael.movieflexapi.api.tvshow.repository.ITVShowRepository
import com.ismael.movieflexapi.api.tvshow.repository.TVShowRepository

class Repository(val presenter: Presenter) : Callbacks.Repository {
    val movieRepository: IMovieRepository = MovieRepository()
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

    @SuppressLint("CheckResult")
    override fun getMovieDetail(item_id: Int, api_key: String?) {
        movieRepository.getMovieDetail(item_id,api_key)
            ?.subscribe( { detailData ->
                presenter.onGetContentList(detailData)
            }){throwable: Throwable ->
                presenter.onFailure(throwable.message.toString())
            }
    }

    @SuppressLint("CheckResult")
    override fun getTVDetail(item_id: Int, api_key: String?) {
        tvShowRepository.getTVDetail(item_id,api_key)
            ?.subscribe( { detailData ->
                presenter.onGetContentList(detailData)
            }){throwable: Throwable ->
                presenter.onFailure(throwable.message.toString())
            }
    }
}