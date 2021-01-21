package hr.fer.projekt.smartAgriculture.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hr.fer.projekt.smartAgriculture.model.CultureModel
import hr.fer.projekt.smartAgriculture.model.DeviceModel
import hr.fer.projekt.smartAgriculture.model.User
import hr.fer.projekt.smartAgriculture.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class DeviceViewModel(private val repository: Repository) : ViewModel() {

    val responseLiveData: MutableLiveData<Response<List<DeviceModel>>> = MutableLiveData()

    var responseLiveDataAddDeviceToCulture: MutableLiveData<Unit> = MutableLiveData()

    var responseLiveDataRemoveDeviceFromCulture: MutableLiveData<Response<Any>?> = MutableLiveData()
    /* ________________*/

    val responseLiveDataGetDeviceForCulture: MutableLiveData<MutableList<DeviceModel>> =
        MutableLiveData()

    val cultureModel: MutableLiveData<List<CultureModel>> =
        MutableLiveData()

    fun getDeviceForCulture(token: String, cultureId: Long) {
        viewModelScope.launch {
            val response = repository.getAllCultures(token)
            cultureModel.value = response.body()
            for (model in cultureModel.value!!) {
                if (model.cultureId.equals(cultureId)) {
                    responseLiveDataGetDeviceForCulture.value = model.devices.toMutableList()
                }
            }
        }
    }
    /*-------------------------*/

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
            repository.deleteDeviceFromCulture(token, cultureId, devId)
            //getDeviceForCulture(token, cultureId)
            //var listOfDevices = responseLiveDataGetDeviceForCulture.value
            /*lateinit var deviceToRemove: DeviceModel
            for (device in responseLiveDataGetDeviceForCulture.value!!) {
                if (device.id == devId) {
                    //listOfDevices?.remove(device)
                    deviceToRemove = device
                }
            }
            responseLiveDataGetDeviceForCulture.value!!.remove(deviceToRemove)*/
        }
    }

}