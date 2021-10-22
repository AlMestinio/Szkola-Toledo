package pl.ostrowskiartur.szkolatoledo.ui.login

import android.content.Context
import android.widget.EditText
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pl.ostrowskiartur.szkolatoledo.R
import pl.ostrowskiartur.szkolatoledo.model.User

class LoginPresenter: LoginContract.Presenter {
    companion object {
        private const val USERS = "users"
    }

    lateinit var view: LoginContract.View
    lateinit var context: Context

    override fun attachView(baseView: LoginContract.View, baseContext: Context) {
        view = baseView
        context = baseContext
        view.initView()
    }

    override fun login(email: EditText?, password: EditText?, database: FirebaseDatabase, auth: FirebaseAuth) {
        if (email == null || email.text.isNullOrEmpty()) {
            view.showEditTextError(email)
        } else if (password == null || password.text.isNullOrEmpty()) {
            view.showEditTextError(password)
        } else {
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener {
                if (it.isSuccessful) {
                    val userRef = database.getReference(USERS).child(auth.currentUser!!.uid)
                    userRef.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val user = snapshot.getValue(User::class.java)
                            userRef.removeEventListener(this)
                            view.showHomeView()
                        }

                        override fun onCancelled(error: DatabaseError) = view.showFirebaseError(context.getString(R.string.firebase_exceptions_unexpected_error_try_later))

                    })

                } else {
                    view.showFirebaseError(
                        when (it.exception) {
                            is FirebaseAuthException -> {
                                (it.exception as FirebaseAuthException).errorCode
                            }
                            is FirebaseTooManyRequestsException -> {
                                (it.exception as FirebaseTooManyRequestsException).message
                            }
                            else -> ""
                        }
                    )
                }
            }
        }
    }

}