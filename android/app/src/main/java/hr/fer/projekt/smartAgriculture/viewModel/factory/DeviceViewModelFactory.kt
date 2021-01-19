package hr.fer.projekt.smartAgriculture.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hr.fer.projekt.smartAgriculture.repository.Repository
import hr.fer.projekt.smartAgriculture.viewModel.DeviceViewModel

class DeviceViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DeviceViewModel(repository) as T
    }

}