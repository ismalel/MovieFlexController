package com.ismael.movieflexcontroller.logic

import android.content.Context
import com.ismael.movieflexbussinesslogic.network.CheckNetwork
import com.ismael.movieflexcontroller.main.MainContract
import com.ismael.movieflexcontroller.remote.Presenter

class MainController(val context: Context, val view: MainContract.View ) {
    private var checkNetwork: CheckNetwork = CheckNetwork(context)
    var presenter: Presenter= Presenter(context)

    init {

    }
}