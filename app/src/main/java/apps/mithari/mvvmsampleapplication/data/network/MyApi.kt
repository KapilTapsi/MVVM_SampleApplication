package apps.mithari.mvvmsampleapplication.data.network

import apps.mithari.mvvmsampleapplication.data.network.responses.AuthResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {
    //    here we put our retrofit network calls
//    this is used to proper format the url to be sent and
//    with that we need to use @Field variable in function body
    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse> // earlier it was returning call now it is returning response
//    suspend is feature of kotlin. suspend function can be paused and resumed at later time
//    they can execute long running operations and wait for them without blocking

    companion object {
        //        this works like static class
//        if we use only object this should be parent class
//        if we use companion object then it should be inside some class
//        companion itself means that it is with someone
        operator fun invoke(): MyApi {
//    we used operator keyword to redefine how the existing function should work
//    and with invoke() we can simply call it using interfacename()()
            return Retrofit.Builder()
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}