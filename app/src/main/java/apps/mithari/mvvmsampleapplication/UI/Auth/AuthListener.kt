package apps.mithari.mvvmsampleapplication.ui.auth

import apps.mithari.mvvmsampleapplication.data.db.entities.User

interface AuthListener {
    //    as we are performing network operations we have 3 functions
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}