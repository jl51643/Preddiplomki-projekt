package hr.fer.projekt.smartAgriculture.model

import java.util.*

data class MeasurementModel(
    val id: Long,
    val devId: String,
    val time: Date,
    val airHumidity: Float,
    val soilHumidity: Float,
    val airTemperature: Float,
    val soilTemperature: Float)
