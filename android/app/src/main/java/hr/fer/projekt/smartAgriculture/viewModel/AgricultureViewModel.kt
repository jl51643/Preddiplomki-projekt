package hr.fer.projekt.smartAgriculture.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.fer.projekt.smartAgriculture.model.CultureModel
import hr.fer.projekt.smartAgriculture.model.DeviceModel
import hr.fer.projekt.smartAgriculture.model.MeasurementModel
import hr.fer.projekt.smartAgriculture.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.net.URI

class AgricultureViewModel(private val repository: Repository) : ViewModel() {

    val responseLiveDataGetLastMeasurements: MutableLiveData<Response<List<MeasurementModel>>> =
        MutableLiveData()

    val responseLiveDataAddCulture: MutableLiveData<Response<URI>> = MutableLiveData()

    val responseLiveDataGetCultures: MutableLiveData<Response<List<CultureModel>>> =
        MutableLiveData()

    var responseLiveDataAddDeviceToCulture: MutableLiveData<Unit> = MutableLiveData()

    var responseLiveDataDeleteCulture: MutableLiveData<Unit> = MutableLiveData()

    var responseLiveDataRemoveDeviceFromCulture: MutableLiveData<Unit> = MutableLiveData()


    fun getLastMeasurements(token: String) {
        viewModelScope.launch {
            val response = repository.getMeasurements(token)
            responseLiveDataGetLastMeasurements.value = response
        }
    }

    fun addCulture(token: String, cultureModel: CultureModel) {
        viewModelScope.launch {
            val response = repository.addCulture(token, cultureModel)
            responseLiveDataAddCulture.value = response
        }
    }

    fun deleteCulture(token: String, id: Long) {
        viewModelScope.launch {
            val response = repository.deleteCulture(token, id)
            responseLiveDataDeleteCulture.value = response
        }
    }

    fun addDeviceToCulture(token: String, id: Long, devId: Long) {
        viewModelScope.launch {
            val response = repository.addDeviceToCulture(token, id, devId)
            responseLiveDataAddDeviceToCulture.value = response
        }
    }

    fun getCultures(token: String) {
        viewModelScope.launch {
            val response = repository.getAllCultures(token)
            responseLiveDataGetCultures.value = response
        }
    }

    fun deleteDeviceFromCulture(token: String, cultureId: Long, devId: Long) {
        viewModelScope.launch {
            val response = repository.deleteDeviceFromCulture(token, cultureId, devId)
            responseLiveDataRemoveDeviceFromCulture.value = response
        }
    }


}