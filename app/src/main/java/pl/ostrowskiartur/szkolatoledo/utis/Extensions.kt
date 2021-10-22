package pl.ostrowskiartur.szkolatoledo.utis

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.getSimpleName(): String = this::class.java.simpleName

fun Any.showELog(log: String) = Log.e(this::class.java.simpleName, log)
fun Any.showELog(log: Throwable) = Log.e(this::class.java.simpleName, log.message ?: "error")

fun Fragment.toast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

infix fun <T> Boolean.then(param: T): T? = if (this) param else null

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}