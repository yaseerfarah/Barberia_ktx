package eg.com.invive.barberia_ktx.Utils

import androidx.fragment.app.Fragment

fun Fragment.showMessage(message: String?) {
    message.takeUnless { message.isNullOrBlank() }?.let { msg ->
        view.snakeBar(msg)
    }
}

fun Fragment.showMessage(resId: Int) {
    view.snakeBar(getString(resId))
}

fun Fragment.toast(message: String?) {
    message.takeUnless { message.isNullOrBlank() }?.let { msg ->
        view.toast(msg)
    }
}

fun Fragment.toast(resId: Int) {
    view.toast(getString(resId))
}