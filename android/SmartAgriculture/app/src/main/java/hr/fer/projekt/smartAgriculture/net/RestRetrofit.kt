package hr.fer.projekt.smartAgriculture.net

import hr.fer.projekt.smartAgriculture.model.MeasurementModel
import retrofit.RestAdapter

class RestRetrofit : RestInterface {

    private val service : Service

    init {
        val baseURL = "http://" + RestFactory.BASE_IP + ":8080"
        val retrofit = RestAdapter.Builder()
            .setEndpoint(baseURL)
            .build()

        service = retrofit.create(Service::class.java)
    }

    override fun getAllMeasurements(): List<MeasurementModel> {
        return service.listOfAllMeasurement
    }
}