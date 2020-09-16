package com.teckathon.missingpeopleapp.data.network

import com.teckathon.missingpeopleapp.data.database.entities.Missing
import com.teckathon.missingpeopleapp.data.network.responses.MissingResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PeopleApi {

    @GET("missing")
    suspend fun getMissing(): Response<Array<Missing>>

    companion object {
        operator fun invoke(networkIntercepter: NetworkIntercepter): PeopleApi {

            return Retrofit.Builder()
                .client(OkHttpClient.Builder().addInterceptor(networkIntercepter).build())
                .baseUrl("https://2oj19npx8j.execute-api.eu-central-1.amazonaws.com/dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PeopleApi::class.java)
        }
    }

}