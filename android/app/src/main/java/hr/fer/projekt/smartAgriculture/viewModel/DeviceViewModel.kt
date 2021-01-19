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

    fun getDevices(token: String) {
        viewModelScope.launch {
            val response = repository.getAllDevices(token)
            responseLiveData.value = response
        }
    }

}