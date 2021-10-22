package pl.ostrowskiartur.szkolatoledo.ui

interface BaseView {
    fun initView()
    fun showToast(message: String)
    fun isProgressBarVisible(isVisible: Boolean)
}