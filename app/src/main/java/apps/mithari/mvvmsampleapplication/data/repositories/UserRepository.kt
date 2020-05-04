package apps.mithari.mvvmsampleapplication.data.repositories

import apps.mithari.mvvmsampleapplication.data.network.MyApi
import apps.mithari.mvvmsampleapplication.data.network.SafeApiRequest
import apps.mithari.mvvmsampleapplication.data.network.responses.AuthResponse

class UserRepository : SafeApiRequest() {
    //    we extended safeapirequest to use function apiRequest here
    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { MyApi().userLogin(email, password) }
    }
}