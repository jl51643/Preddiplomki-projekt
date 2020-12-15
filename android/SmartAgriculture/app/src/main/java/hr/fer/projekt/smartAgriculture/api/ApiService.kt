package hr.fer.projekt.smartAgriculture.api

import hr.fer.projekt.smartAgriculture.model.MeasurementModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/api/measurement/all")
    suspend fun getMeasurements() : Response<List<MeasurementModel>>
}