package pl.ostrowskiartur.szkolatoledo.ui.login

import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import pl.ostrowskiartur.szkolatoledo.ui.BasePresenter
import pl.ostrowskiartur.szkolatoledo.ui.BaseView


interface LoginContract {

    interface View: BaseView {
        fun showRegisterView()
        fun showHomeView()
        fun showEditTextError(view: EditText?)
        fun showFirebaseError(exception: String?)
    }

    interface Presenter: BasePresenter<View> {
        fun login(email: EditText?, password: EditText?, database: FirebaseDatabase, auth: FirebaseAuth)
    }

}