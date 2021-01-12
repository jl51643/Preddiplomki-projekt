package hr.fer.projekt.smartAgriculture.repository

import hr.fer.projekt.smartAgriculture.api.RetrofitInstance
import hr.fer.projekt.smartAgriculture.model.*
import retrofit2.Response
import retrofit2.http.Header
import java.net.URI

class Repository {

    suspend fun getMeasurements(token: String) : Response<List<MeasurementModel>> {
        return RetrofitInstance.api.getMeasurements(token)
    }

    suspend fun registerUser(registrationModel: RegistrationModel) : Response<TokenModel> {
        return RetrofitInstance.api.registerUser(registrationModel)
    }

    suspend fun login(loginModel: LoginModel) : Response<TokenModel> {
        return RetrofitInstance.api.login(loginModel)
    }

    suspend fun addCulture(token: String, cultureModel: CultureModel): Response<URI> {
        return RetrofitInstance.api.addCulture(token, cultureModel)
    }

    suspend fun getAllCultures(token: String) : Response<List<CultureModel>> {
        return RetrofitInstance.api.getAllCultures(token)
    }

    suspend fun deleteCulture(token: String, id: Long) {
        RetrofitInstance.api.deleteCulture(token, id)
    }

    suspend fun addDeviceToCulture(token: String, id: Long, deviceModel: DeviceModel) {
        return RetrofitInstance.api.addDeviceToCulture(token, id, deviceModel)
    }

    suspend fun deleteDeviceFromCulture(token: String, cultureId: Long, devId: Long) {
        return RetrofitInstance.api.deleteDeviceFromCulture(token, cultureId, devId)
    }
}