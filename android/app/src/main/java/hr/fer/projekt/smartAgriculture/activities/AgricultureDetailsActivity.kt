package hr.fer.projekt.smartAgriculture.activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.model.DeviceModel
import hr.fer.projekt.smartAgriculture.model.User
import hr.fer.projekt.smartAgriculture.repository.Repository
import hr.fer.projekt.smartAgriculture.viewModel.*
import hr.fer.projekt.smartAgriculture.viewModel.factory.DeviceViewModelFactory
import kotlinx.android.synthetic.main.activity_agriculture_details.*
import kotlinx.android.synthetic.main.activity_log_in.*

class AgricultureDetailsActivity : AppCompatActivity() {

    lateinit var viewModel: DeviceViewModel
    var id: MutableList<Long> = ArrayList()
    var devId: MutableMap<Long, String> = LinkedHashMap()
    var devIdList: MutableList<String> = ArrayList()
    lateinit var arrrr: Array<String>
    var intentId: Long = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agriculture_details)

        val devicesSpinner: Spinner = findViewById(R.id.devices_spinner)


        val repository = Repository()
        val viewModelFactory = DeviceViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DeviceViewModel::class.java)
        viewModel.getDevices("Bearer ${User.user.token}")
        /*viewModel.getDevices("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrb3Jpc25payIsImlhdCI6MTYxMTAzMDQzMSwiZXhwIjoxNjExMTE2ODMxfQ.ft8xZvtCI54zLlIglOFv6J0imOkofMQOSGyeKmBSdFzmwCGU7yqWjdORGc8zRKPrzHnI9pb-BD3ys3ohumaeMQ")*/


        viewModel.responseLiveData.observe(this, Observer { response ->
            val listOfDevices: List<DeviceModel>? = response.body()
            println(listOfDevices)

            if (listOfDevices != null) {
                for (model in listOfDevices) {
                    devId.put(model.id, model.devId)
                    devIdList.add(model.devId)
                    /*
                    println("${model.id} ${model.devId}")*/
                }
            }

        })

        for (value in devId.values) {
        }

        val arr = arrayOf("ferDev1", "ferDev2", "fer-pycom-device", "waspmote_device_iot")

        val array = arrayListOf(devIdList)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, array[0])

        devicesSpinner.adapter = adapter

        devicesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                intentId = position.toLong()
                println(intentId)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(
                    this@AgricultureDetailsActivity,
                    "Please select a device ",
                    Toast.LENGTH_LONG
                ).show()
            }

        }

        saveAgricultureButton.setOnClickListener {
            if (agricultureTitleEditText.text.toString() != "" && agricultureDescriptionEditText.text.toString() != "") {


                finish()
            }
        }
    }
}