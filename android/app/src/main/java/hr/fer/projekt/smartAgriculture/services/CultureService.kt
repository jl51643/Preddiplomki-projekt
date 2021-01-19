package hr.fer.projekt.smartAgriculture.services

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import hr.fer.projekt.smartAgriculture.model.CultureModel
import hr.fer.projekt.smartAgriculture.repository.Repository
import hr.fer.projekt.smartAgriculture.viewModel.AgricultureViewModel
import hr.fer.projekt.smartAgriculture.viewModel.DeviceViewModel
import hr.fer.projekt.smartAgriculture.viewModel.factory.AgricultureViewModelFactory

class CultureService : AppCompatActivity() {

    private lateinit var viewModel: AgricultureViewModel
    private lateinit var deviceViewModel: DeviceViewModel

    fun addCulture(token: String, cultureModel: CultureModel) {
        val repository = Repository()
        val viewModelFactory = AgricultureViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(AgricultureViewModel::class.java)
        viewModel.addCulture(token, cultureModel)

    }

    fun getAllCultures(token: String) {
        val repository = Repository()
        val viewModelFactory = AgricultureViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(AgricultureViewModel::class.java)
        viewModel.getCultures(token)
    }

    fun deleteCulture(token: String, id: Long) {
        val repository = Repository()
        val viewModelFactory = AgricultureViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(AgricultureViewModel::class.java)
        viewModel.deleteCulture(token, id)
    }

    fun addDeviceToCulture(token: String, id: Long, devId: Long) {
        val repository = Repository()
        val viewModelFactory = AgricultureViewModelFactory(repository)

        deviceViewModel = ViewModelProvider(this, viewModelFactory).get(DeviceViewModel::class.java)
        deviceViewModel.addDeviceToCulture(token, id, devId)
    }

    fun deleteDeviceFromCulture(token: String, cultureId: Long, devId: Long) {
        val repository = Repository()
        val viewModelFactory = AgricultureViewModelFactory(repository)

        deviceViewModel = ViewModelProvider(this, viewModelFactory).get(DeviceViewModel::class.java)
        deviceViewModel.deleteDeviceFromCulture(token, cultureId, devId)
    }

}