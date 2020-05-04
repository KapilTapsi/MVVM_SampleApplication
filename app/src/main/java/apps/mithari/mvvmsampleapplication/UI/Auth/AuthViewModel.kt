package apps.mithari.mvvmsampleapplication.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import apps.mithari.mvvmsampleapplication.data.repositories.UserRepository
import apps.mithari.mvvmsampleapplication.util.ApiException
import apps.mithari.mvvmsampleapplication.util.Coroutines

class AuthViewModel(private val repository: UserRepository) : ViewModel() {
    var email: String? = null
    var password: String? = null
    var passwordconfirm: String? = null
    var name: String? = null
    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }
        Coroutines.main {// this is an extension function we created in utils
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)  //it will save the user into database
                    return@main
                } // if everything is good to go then return
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
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