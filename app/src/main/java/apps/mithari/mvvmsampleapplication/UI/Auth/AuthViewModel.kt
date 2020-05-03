package apps.mithari.mvvmsampleapplication.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    var email: String? = null
    var password: String? = null
    var passwordconfirm: String? = null
    var name: String? = null
    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }
        authListener?.onSuccess()
    }

    fun onSignup(view: View) {

    }

    fun onSignupButtonClick(view: View) {

    }

    fun onLogin(view: View) {

    }
}