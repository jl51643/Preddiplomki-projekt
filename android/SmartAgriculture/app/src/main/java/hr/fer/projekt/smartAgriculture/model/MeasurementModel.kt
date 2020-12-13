package hr.fer.projekt.smartAgriculture.model

import java.util.*

data class MeasurementModel(val id: Long, val devId: String, val time: Date, val airHumidity: Float, val soilHumidity: Float, val airTemperature: Float, val soilTemperature: Float) {

    //private val id: Long = id
    //private val devId: String = devId
    //private val time: Date = time
    //private val airHumidity: Float = airHumidity
    //private val soilHumidity: Float = soilHumidity
    //private val airTemperature: Float = airTemperature
    //private val soilTemperature: Float = soilTemperature

}
