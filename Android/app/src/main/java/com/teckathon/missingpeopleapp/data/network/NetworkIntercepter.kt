package com.teckathon.missingpeopleapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.teckathon.missingpeopleapp.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkIntercepter(context: Context) : Interceptor {

    private val applicationContext = context.applicationContext

    /**
     * an interceptor used to check if the connection is alive before the request
     * @return request
     * @throws NoInternetException
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw NoInternetException("Make sure you have active internet connection!")
        }
        return chain.proceed(chain.request())
    }

    /**
     * a function to check if the connection is alive
     * @see NetworkCapabilities
     * @return Boolean
     */
    private fun isConnected(): Boolean {
        var isAvailable: Boolean = false
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                isAvailable = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return isAvailable
    }

}