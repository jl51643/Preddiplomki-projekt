package hr.fer.projekt.smartAgriculture.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.fer.projekt.smartAgriculture.model.CultureModel
import hr.fer.projekt.smartAgriculture.model.DeviceModel
import hr.fer.projekt.smartAgriculture.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class DeviceViewModel(private val repository: Repository) : ViewModel() {

    val responseLiveData: MutableLiveData<Response<List<DeviceModel>>> = MutableLiveData()

    var responseLiveDataAddDeviceToCulture: MutableLiveData<Unit> = MutableLiveData()

    var responseLiveDataRemoveDeviceFromCulture: MutableLiveData<Unit> = MutableLiveData()

    fun getDevices(token: String) {
        viewModelScope.launch {
            val response = repository.getAllDevices(token)
            responseLiveData.value = response
        }
    }

    fun addDeviceToCulture(token: String, id: Long, devId: Long) {
        viewModelScope.launch {
            val response = repository.addDeviceToCulture(token, id, devId)
            responseLiveDataAddDeviceToCulture.value = response
        }
    }

    fun deleteDeviceFromCulture(token: String, cultureId: Long, devId: Long) {
        viewModelScope.launch {
            val response = repository.deleteDeviceFromCulture(token, cultureId, devId)
            responseLiveDataRemoveDeviceFromCulture.value = response
        }
    }

}