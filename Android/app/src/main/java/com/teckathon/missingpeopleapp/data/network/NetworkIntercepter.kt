package com.teckathon.missingpeopleapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.teckathon.missingpeopleapp.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkIntercepter(context: Context): Interceptor {

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
     * @return Boolean
     */
    private fun isConnected(): Boolean {
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }

}