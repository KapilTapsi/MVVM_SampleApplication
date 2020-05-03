package apps.mithari.mvvmsampleapplication.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import apps.mithari.mvvmsampleapplication.repositories.UserRepository

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
        val loginResponse = UserRepository().userLogin(email!!, password!!)
        authListener?.onSuccess(loginResponse)
    }

    fun onSignup(view: View) {

    }

    fun onSignupButtonClick(view: View) {

    }

    fun onLogin(view: View) {

    }
}