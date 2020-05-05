package apps.mithari.mvvmsampleapplication.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import apps.mithari.mvvmsampleapplication.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {
//    this class is an interceptor and stops execution of "chain" if no network is there

//    we will add this interceptor in MyApi interface

    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable())
            throw NoInternetException("Make sure you have an active data connection")

        return chain.proceed(chain.request())
//        if the network is not available then it throws exception or if network is present
//        then it will simply return chain.proceed(chain.request())
    }

    //        now we need a function to check if network is present or not
    private fun isNetworkAvailable(): Boolean {
        var status: Boolean = false
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.run {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || hasTransport(
                        NetworkCapabilities.TRANSPORT_CELLULAR
                    ) || hasTransport(NetworkCapabilities.TRANSPORT_VPN)
                ) {
                    status = true
                }
            }
        }
        return status
    }
}
