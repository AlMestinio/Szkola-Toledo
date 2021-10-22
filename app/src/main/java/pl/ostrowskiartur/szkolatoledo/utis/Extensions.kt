package pl.ostrowskiartur.szkolatoledo.utis

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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