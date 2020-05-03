package apps.mithari.mvvmsampleapplication.ui.auth

interface AuthListener {
    //    as we are performing network operations we have 3 functions
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}