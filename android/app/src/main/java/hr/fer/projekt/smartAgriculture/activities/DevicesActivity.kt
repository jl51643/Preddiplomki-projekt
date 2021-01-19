package hr.fer.projekt.smartAgriculture.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.model.DeviceModel
import hr.fer.projekt.smartAgriculture.model.User
import hr.fer.projekt.smartAgriculture.repository.Repository
import hr.fer.projekt.smartAgriculture.viewModel.DeviceViewModel
import hr.fer.projekt.smartAgriculture.viewModel.factory.DeviceViewModelFactory

class DevicesActivity : AppCompatActivity() {

    lateinit var viewModel: DeviceViewModel
    val viewModelFactory = DeviceViewModelFactory(Repository())
    var id: MutableList<Long> = ArrayList()
    var devId: MutableList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices)

        viewModel = ViewModelProvider(this, viewModelFactory).get(DeviceViewModel::class.java)
        viewModel.getDevices("Bearer ${User.user.token}")

        viewModel.responseLiveData.observe(this, Observer { response ->

            if (response.isSuccessful) {
                val listOfDevices: List<DeviceModel>? = response.body()
                println(listOfDevices)

                if (listOfDevices != null) {
                    for (model in listOfDevices) {
                        id.add(model.id)
                        devId.add(model.devId)

                        println("${model.id} ${model.devId}")
                    }
                }

            } else {

            }
        })



    }

    override fun onResume() {
        super.onResume()
    }

}