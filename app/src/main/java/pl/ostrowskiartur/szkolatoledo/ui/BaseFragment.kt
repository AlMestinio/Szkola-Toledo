package pl.ostrowskiartur.szkolatoledo.ui

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import pl.ostrowskiartur.szkolatoledo.MainActivity
import pl.ostrowskiartur.szkolatoledo.utis.toast

open class BaseFragment: Fragment(), BaseView {

    open lateinit var mainActivity: MainActivity

    protected val database = Firebase.database
    protected val auth: FirebaseAuth = Firebase.auth

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = (activity as MainActivity)
    }

    override fun onResume() {
        super.onResume()
        mainActivity.updateToolbar()
    }

    override fun initView() {}

    override fun showToast(message: String) = toast(message)

    override fun isProgressBarVisible(isVisible: Boolean) = mainActivity.changeProgressBarVisibility(isVisible)
}