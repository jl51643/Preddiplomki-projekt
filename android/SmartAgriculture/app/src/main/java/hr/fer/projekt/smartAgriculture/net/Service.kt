package hr.fer.projekt.smartAgriculture.net

import hr.fer.projekt.smartAgriculture.model.MeasurementModel
import retrofit.http.GET

interface Service {

    @get:GET("/all")
    val listOfAllMeasurement: List<MeasurementModel>
}