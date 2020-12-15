package hr.fer.projekt.smartAgriculture.net

import hr.fer.projekt.smartAgriculture.model.MeasurementModel
import retrofit.http.GET

interface Service {

    @GET("/api/measurement/all")
    fun getAllMeasurements(): List<MeasurementModel>
}