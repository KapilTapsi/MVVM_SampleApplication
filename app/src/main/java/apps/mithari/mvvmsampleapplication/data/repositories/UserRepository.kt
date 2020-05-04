package apps.mithari.mvvmsampleapplication.data.repositories

import apps.mithari.mvvmsampleapplication.data.db.AppDatabase
import apps.mithari.mvvmsampleapplication.data.db.entities.User
import apps.mithari.mvvmsampleapplication.data.network.MyApi
import apps.mithari.mvvmsampleapplication.data.network.SafeApiRequest
import apps.mithari.mvvmsampleapplication.data.network.responses.AuthResponse

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {
//    we extended safeapirequest to use function apiRequest here.
//    to avoid UserRepository class to be depended on the instance of myApi and mydatabase classes
//    we will pass the instances from constructor instead of creating them in class itself

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    //    we create the function saveuser to save the current user details in the database
//    that too using coroutines or asynchronously
    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)
//    this method supposed to save user details and update the database

    fun getUser() = db.getUserDao().getuser()

}