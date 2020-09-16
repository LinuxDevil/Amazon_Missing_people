package com.teckathon.missingpeopleapp.data.network

import com.teckathon.missingpeopleapp.data.database.entities.Missing
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
     * @param name String
     * @param motherName String?
     * @param nationalNumber String?
     * @param gender String?
     * @param birthDate String?
     * @param birthPlace String?
     * @param photoUrl String?
     * @param missingSince String?
     * @param lastKnownLocation String?
     * @param contactNumber String?
     * @return Response<InformMissingResponse>
     */
    @FormUrlEncoded
    @POST("missing")
    suspend fun addMissing(
        @Field("name")
        name: String,
        @Field("mother_name")
        motherName: String,
        @Field("national_number")
        nationalNumber: String,
        @Field("gender")
        gender: String,
        @Field("birth_date")
        birthDate: String,
        @Field("birth_place")
        birthPlace: String,
        @Field("photo")
        photoUrl: String,
        @Field("missing_since")
        missingSince: String,
        @Field("last_known_location")
        lastKnownLocation: String,
        @Field("contact_number")
        contactNumber: String
    ): Response<InformMissingResponse>

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