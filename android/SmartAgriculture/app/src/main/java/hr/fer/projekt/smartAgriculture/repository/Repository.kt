package hr.fer.projekt.smartAgriculture.repository

import hr.fer.projekt.smartAgriculture.api.RetrofitInstance
import hr.fer.projekt.smartAgriculture.model.MeasurementModel
import retrofit2.Response

class Repository {

    suspend fun getMeasurements() : Response<List<MeasurementModel>> {
        return RetrofitInstance.api.getMeasurements()
    }
}