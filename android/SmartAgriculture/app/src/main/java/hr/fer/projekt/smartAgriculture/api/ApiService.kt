package hr.fer.projekt.smartAgriculture.api

import hr.fer.projekt.smartAgriculture.model.*
import retrofit2.Response
import retrofit2.http.*
import java.net.URI

interface ApiService {

    @GET("/api/measurement/all")
    suspend fun getMeasurements(@Header("Authorization") token: String) : Response<List<MeasurementModel>>

    @POST("/api/auth/register")
    suspend fun registerUser(@Body registrationModel: RegistrationModel) : Response<TokenModel>

    @POST("/api/auth/login")
    suspend fun login(@Body loginModel: LoginModel) : Response<TokenModel>

    @POST("/api/culture/add")
    suspend fun addCulture(@Header("Authorization") token: String, @Body cultureModel: CultureModel) : Response<URI>

    @GET("/api/culture/all")
    suspend fun getAllCultures(@Header("Authorization") token: String) : Response<List<CultureModel>>

    @DELETE("/api/culture/{id}")
    suspend fun deleteCulture(@Header("Authorization") token: String, @Path("id") id: Long)

    @POST("/api/culture/{id}/devices/add")
    suspend fun addDeviceToCulture(@Header("Authorisation") token: String, @Path("id") id: Long, @Body deviceModel: DeviceModel)

    @DELETE("api/culture/{cultureId}/devices/delete/{devId}")
    suspend fun deleteDeviceFromCulture(@Header("Authorisation") token: String, @Path("cultureId") cultureId: Long, @Path("devId") devId: Long)
}