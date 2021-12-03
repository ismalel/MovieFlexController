package com.ismael.movieflexcontroller.detail

import android.annotation.SuppressLint
import android.app.Activity
import com.ismael.movieflexapi.api.movie.repository.IMovieRepository
import com.ismael.movieflexapi.api.movie.repository.MovieRepository
import com.ismael.movieflexapi.api.tvshow.repository.ITVShowRepository
import com.ismael.movieflexapi.api.tvshow.repository.TVShowRepository

class DetailInteractor(val activity: Activity, val presenter: DetailContract.Presenter) : DetailContract.Interactor {

    val movieRepository: IMovieRepository = MovieRepository()
    val tvRepository: ITVShowRepository = TVShowRepository()

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
        tvRepository.getTVDetail(item_id,api_key)
            ?.subscribe( { detailData ->
                presenter.onGetContentList(detailData)
            }){throwable: Throwable ->
                presenter.onFailure(throwable.message.toString())
            }
    }

}