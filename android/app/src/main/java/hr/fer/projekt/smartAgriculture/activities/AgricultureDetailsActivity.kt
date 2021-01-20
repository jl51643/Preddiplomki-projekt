package hr.fer.projekt.smartAgriculture.activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.model.CultureModel
import hr.fer.projekt.smartAgriculture.model.DeviceModel
import hr.fer.projekt.smartAgriculture.model.User
import hr.fer.projekt.smartAgriculture.repository.Repository
import hr.fer.projekt.smartAgriculture.viewModel.*
import hr.fer.projekt.smartAgriculture.viewModel.factory.AgricultureViewModelFactory
import hr.fer.projekt.smartAgriculture.viewModel.factory.DeviceViewModelFactory

class AgricultureDetailsActivity : AppCompatActivity() {

    lateinit var viewModel: DeviceViewModel
    var id: MutableList<Long> = ArrayList()

    ///
    var devId: MutableMap<Long, String> = LinkedHashMap()
    var devIdList: MutableList<String> = ArrayList()
    var devices: List<DeviceModel>? = null

    ///
    lateinit var arrrr: Array<String>
    var intentId: Long = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agriculture_details)

        val saveAgricultureButton = findViewById<Button>(R.id.saveAgricultureButton)
        val agricultureTitleEditText = findViewById<EditText>(R.id.agricultureTitleEditText)
        val agricultureDescriptionEditText =
            findViewById<EditText>(R.id.agricultureDescriptionEditText)
        val devicesSpinner: Spinner = findViewById(R.id.devices_spinner)


        val repository = Repository()
        val viewModelFactory = DeviceViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DeviceViewModel::class.java)
        viewModel.getDevices("Bearer ${User.user.token}")


        viewModel.responseLiveData.observe(this, Observer { response ->
            val listOfDevices: List<DeviceModel>? = response.body()
            println(listOfDevices)

            if (listOfDevices != null) {
                devices = listOfDevices
                for (model in listOfDevices) {
                    devId.put(model.id, model.devId)
                    devIdList.add(model.devId)
                    /*
                    println("${model.id} ${model.devId}")*/
                }
            }

        })

        val adapter = OPTIONS?.let { ArrayAdapter(this, android.R.layout.simple_spinner_item, it) }

        devicesSpinner.adapter = adapter

        devicesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                intentId = position.toLong()
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
                val devices: List<DeviceModel> = listOf(devicesSpinner.selectedItem as DeviceModel)
                val cultureModel = CultureModel(
                    cultureId = -1,
                    title = agricultureTitleEditText.text.toString(),
                    devices = devices,
                    description = agricultureDescriptionEditText.text.toString()
                )

                val viewModelFactory = AgricultureViewModelFactory(repository)
                val agricultureViewModel =
                    ViewModelProvider(this@AgricultureDetailsActivity, viewModelFactory).get(
                        AgricultureViewModel::class.java
                    )
                agricultureViewModel.addCulture("Bearer ${User.user.token}", cultureModel)
                finish()
            }
        }
    }

    companion object {
        var OPTIONS: Array<DeviceModel>? = null
    }
}