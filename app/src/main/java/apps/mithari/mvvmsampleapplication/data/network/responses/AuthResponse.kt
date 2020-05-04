package apps.mithari.mvvmsampleapplication.data.network.responses

import apps.mithari.mvvmsampleapplication.data.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User? // we have defined User already in date/db/entities/User
)
//    this is mapping class for the auth response from json response
