package apps.mithari.mvvmsampleapplication.data.network

import apps.mithari.mvvmsampleapplication.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

// we will handle the errors of the api in this class
abstract class SafeApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
//        we created the suspend function of type any <T : Any> and will
//        give response as T
//        inside apiRequest function we will pass another suspend function with no parameter
//        and will give response of type Any or T


        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.toString() // in example string() was used
//            read somewhere that string() handles null but toString() doesn't
            val message = StringBuilder()
            try {
                error?.let { message.append(JSONObject(it).getString("message")) }
            } catch (e: JSONException) {
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")
            throw ApiException(message.toString())
        }

    }
}