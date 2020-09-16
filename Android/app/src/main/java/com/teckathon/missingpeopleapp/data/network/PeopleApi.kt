package com.teckathon.missingpeopleapp.data.network

import com.teckathon.missingpeopleapp.data.database.entities.Missing
import com.teckathon.missingpeopleapp.data.database.entities.MissingO
import com.teckathon.missingpeopleapp.data.network.responses.InformMissingResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface PeopleApi {

    /**
     *
     * @return Response<Array<Missing>>
     */
    @GET("missing")
    suspend fun getMissing(): Response<Array<Missing>>

    /**
     *
      * @param body MissingO
     * @return Response<InformMissingResponse>
     */
    @Headers("Content-Type: application/json")
    @POST("missing")
    suspend fun addMissing(@Body body: MissingO): Response<InformMissingResponse>

    /**
     *
     * @param body MissingO
     * @return Response<InformMissingResponse>
     */
    @Headers("Content-Type: application/json")
    @POST("found")
    suspend fun addFound(@Body body: MissingO): Response<InformMissingResponse>


    companion object {
        operator fun invoke(networkInterceptor: NetworkIntercepter): PeopleApi {

            return Retrofit.Builder()
                .client(OkHttpClient.Builder().addInterceptor(networkInterceptor).build())
                .baseUrl("https://2oj19npx8j.execute-api.eu-central-1.amazonaws.com/dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PeopleApi::class.java)
        }
    }

}