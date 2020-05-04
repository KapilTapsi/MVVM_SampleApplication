package apps.mithari.mvvmsampleapplication.data.repositories

import apps.mithari.mvvmsampleapplication.data.network.MyApi
import apps.mithari.mvvmsampleapplication.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository {
    suspend fun userLogin(email: String, password: String): Response<AuthResponse> {
        return MyApi().userLogin(email, password)
    }
}