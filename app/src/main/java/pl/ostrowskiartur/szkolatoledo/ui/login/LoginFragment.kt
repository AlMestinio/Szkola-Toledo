package pl.ostrowskiartur.szkolatoledo.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_login.*
import pl.ostrowskiartur.szkolatoledo.R
import pl.ostrowskiartur.szkolatoledo.ui.BaseFragment
import pl.ostrowskiartur.szkolatoledo.utis.getSimpleName
import pl.ostrowskiartur.szkolatoledo.utis.hideKeyboard
import pl.ostrowskiartur.szkolatoledo.utis.toast

class LoginFragment: BaseFragment(), LoginContract.View, View.OnClickListener {
    companion object {
        val TAG = LoginFragment().getSimpleName()
    }

    private lateinit var presenter: LoginContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_login, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        presenter = LoginPresenter()
        presenter.attachView(this, requireContext())
    }

    override fun initView() {
        btLogIn.setOnClickListener(this)
    }

    override fun showHomeView() {
        isProgressBarVisible(false)
        mainActivity.navigate(R.id.action_Login_to_home)
    }

    override fun showRegisterView() {
        isProgressBarVisible(false)
    }

    override fun showEditTextError(view: EditText?) {
        isProgressBarVisible(false)
        view?.error = getString(R.string.field_required)
    }

    override fun showFirebaseError(exception: String?) {
        isProgressBarVisible(false)
        toast(when (exception) {
            "ERROR_INVALID_EMAIL" -> { getString(R.string.firebase_exceptions_invalid_email) }
            "ERROR_WRONG_PASSWORD" -> { getString(R.string.firebase_exceptions_wrong_password) }
            "ERROR_USER_DISABLED" -> { getString(R.string.firebase_exceptions_user_disabled) }
            "ERROR_USER_NOT_FOUND" -> { getString(R.string.firebase_exceptions_user_not_found) }
            else -> { getString(R.string.firebase_exceptions_unexpected_error_try_later) }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            btLogIn.id -> {
                hideKeyboard()
                isProgressBarVisible(true)
                presenter.login(etEmail, etPassword, database, auth)
            }
        }
    }

}