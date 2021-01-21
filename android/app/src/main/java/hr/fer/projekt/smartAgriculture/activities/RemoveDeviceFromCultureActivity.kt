package hr.fer.projekt.smartAgriculture.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.model.CultureModel
import hr.fer.projekt.smartAgriculture.model.DeviceModel
import hr.fer.projekt.smartAgriculture.model.User
import hr.fer.projekt.smartAgriculture.repository.Repository
import hr.fer.projekt.smartAgriculture.viewModel.AgricultureViewModel
import hr.fer.projekt.smartAgriculture.viewModel.DeviceViewModel
import hr.fer.projekt.smartAgriculture.viewModel.factory.AgricultureViewModelFactory
import hr.fer.projekt.smartAgriculture.viewModel.factory.DeviceViewModelFactory

class RemoveDeviceFromCultureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_device_from_culture)

        val deviceId = findViewById<TextView>(R.id.device_id)
        val deviceDevId = findViewById<TextView>(R.id.device_dev_id)
        val removeDeviceButton = findViewById<Button>(R.id.remove_device_button)

        val device = intent.getSerializableExtra("device") as DeviceModel
        val cultureId = intent.getLongExtra("cultureId", 0)

        deviceId.text = device.id.toString()
        deviceDevId.text = device.devId

        removeDeviceButton.setOnClickListener {
            val repository = Repository()
            val viewModelFactory = DeviceViewModelFactory(repository)
            val deviceViewModel = ViewModelProvider(this, viewModelFactory).get(DeviceViewModel::class.java)
            deviceViewModel.deleteDeviceFromCulture("Bearer ${User.user.token}", cultureId, device.id)

            val cultureViewModelFactory = AgricultureViewModelFactory(repository)
            val cultureViewModel = ViewModelProvider(this, cultureViewModelFactory).get(AgricultureViewModel::class.java)
            cultureViewModel.getCultures("Bearer ${User.user.token}")
            lateinit var cultureModel: CultureModel
            cultureViewModel.responseLiveDataGetCultures.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    for (culture in response.body()!!) {
                        if (culture.cultureId == cultureId) {
                            cultureModel = culture
                            startActivity(Intent(applicationContext, DevicesActivity::class.java).putExtra("culture", cultureModel))
                            break
                        }
                    }
                }
            })
        }















    }
}