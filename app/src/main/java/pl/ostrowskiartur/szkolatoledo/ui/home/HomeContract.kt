package pl.ostrowskiartur.szkolatoledo.ui.home

import pl.ostrowskiartur.szkolatoledo.ui.BasePresenter
import pl.ostrowskiartur.szkolatoledo.ui.BaseView

interface HomeContract {

    interface View: BaseView {
    }

    interface Presenter: BasePresenter<View> {
    }

}