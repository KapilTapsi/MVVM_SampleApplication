package apps.mithari.mvvmsampleapplication.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import apps.mithari.mvvmsampleapplication.data.repositories.UserRepository
import apps.mithari.mvvmsampleapplication.util.Coroutines

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
        Coroutines.main {
            val response = UserRepository().userLogin(email!!, password!!)
            if (response.isSuccessful) {
                authListener?.onSuccess(response.body()?.user!!)
            } else {
                authListener?.onFailure("Error Code: ${response.code()}")
            }
        }
    }

    fun onSignup(view: View) {

    }

    fun onSignupButtonClick(view: View) {

    }

    fun onLogin(view: View) {

    }
}