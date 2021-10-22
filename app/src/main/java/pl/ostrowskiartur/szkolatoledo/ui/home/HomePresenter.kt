package pl.ostrowskiartur.szkolatoledo.ui.home

import android.content.Context

class HomePresenter: HomeContract.Presenter {

    lateinit var view: HomeContract.View
    lateinit var context: Context

    override fun attachView(baseView: HomeContract.View, baseContext: Context) {
        view = baseView
        context = baseContext
        view.initView()
    }

}