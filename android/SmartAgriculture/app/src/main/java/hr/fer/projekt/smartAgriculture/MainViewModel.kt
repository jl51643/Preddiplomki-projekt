package hr.fer.projekt.smartAgriculture

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.fer.projekt.smartAgriculture.model.MeasurementModel
import hr.fer.projekt.smartAgriculture.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val responseLiveData: MutableLiveData<Response<List<MeasurementModel>>> = MutableLiveData()

    fun getMeasurements() {
        viewModelScope.launch {
            val response = repository.getMeasurements()
            responseLiveData.value = response
        }
    }
}