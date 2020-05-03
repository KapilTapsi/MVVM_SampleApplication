package apps.mithari.mvvmsampleapplication.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {
    //    as we are performing network operations we have 3 functions
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(message: String)
}