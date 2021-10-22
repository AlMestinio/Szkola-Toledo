package pl.ostrowskiartur.szkolatoledo.ui

import android.content.Context

interface BasePresenter<in T> {
    fun attachView(baseView: T, baseContext: Context)
}