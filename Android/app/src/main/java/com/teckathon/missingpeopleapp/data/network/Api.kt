package com.teckathon.missingpeopleapp.data.network

import com.teckathon.missingpeopleapp.data.network.responses.AuthResponse
import com.teckathon.missingpeopleapp.data.network.responses.MissingResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(@Field("email") email: String, @Field("password") password: String) : Response<AuthResponse>

    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignUp(@Field("name") name: String, @Field("email") email: String, @Field("password") password: String): Response<AuthResponse>

    companion object {
        operator fun invoke(networkIntercepter: NetworkIntercepter): Api {

            return Retrofit.Builder()
                .client(OkHttpClient.Builder().addInterceptor(networkIntercepter).build())
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }

}