package hr.fer.projekt.smartAgriculture.net

import hr.fer.projekt.smartAgriculture.model.MeasurementModel

interface RestInterface {

    fun getAllMeasurements() : List<MeasurementModel>
}