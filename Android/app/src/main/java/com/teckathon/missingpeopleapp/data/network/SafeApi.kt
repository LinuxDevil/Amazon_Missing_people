package com.teckathon.missingpeopleapp.data.network

import android.util.Log
import com.teckathon.missingpeopleapp.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.Exception
import java.lang.StringBuilder

abstract class SafeApi {

    suspend fun<T: Any> apiRequest(call: suspend (() -> Response<T>)) : T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!;
        }
        val error = response.errorBody()?.string()
        val message = StringBuilder()
        error?.let {
            try {
                message.append(JSONObject(it).getString("message"))
            } catch (error: JSONException) {

            }
            message.append("\n")
        }
        message.append("Error Code: ${response.code()}")
        throw ApiException(message.toString())
    }


}