package hr.fer.projekt.smartAgriculture.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.model.CultureModel
import hr.fer.projekt.smartAgriculture.model.DeviceModel
import hr.fer.projekt.smartAgriculture.model.User
import hr.fer.projekt.smartAgriculture.repository.Repository
import hr.fer.projekt.smartAgriculture.services.CultureService
import hr.fer.projekt.smartAgriculture.viewModel.AgricultureViewModel
import hr.fer.projekt.smartAgriculture.viewModel.DeviceViewModel
import hr.fer.projekt.smartAgriculture.viewModel.factory.AgricultureViewModelFactory
import hr.fer.projekt.smartAgriculture.viewModel.factory.DeviceViewModelFactory

class DevicesActivity : AppCompatActivity() {

    val repository = Repository()
    val viewModelFactory = DeviceViewModelFactory(repository)
    val agricultureViewModelFactory = AgricultureViewModelFactory(repository)
    lateinit var devicesAdapter: DevicesAdapter
    lateinit var viewModel: DeviceViewModel
    lateinit var devices: List<DeviceModel>
    lateinit var culture: CultureModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices)

        val listOfDevicesView = findViewById<RecyclerView>(R.id.list_of_devices_view)
        val addNewDeviceButton = findViewById<FloatingActionButton>(R.id.addDevicesButton)


        listOfDevicesView.layoutManager = LinearLayoutManager(applicationContext)
        culture = intent.getSerializableExtra("culture") as CultureModel
        val culture: CultureModel = intent.getSerializableExtra("culture") as CultureModel

        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.cell_divider
            )!!
        )
        listOfDevicesView.addItemDecoration(decorator)

        viewModel = ViewModelProvider(this, viewModelFactory).get(DeviceViewModel::class.java)
        devicesAdapter = DevicesAdapter(viewModel, culture.devices)
        listOfDevicesView.adapter = devicesAdapter

        viewModel.responseLiveData.observe(this, Observer {
            devicesAdapter.notifyDataSetChanged()
        })


        addNewDeviceButton.setOnClickListener {

            val devicesViewModelFactory = DeviceViewModelFactory(repository)
            val devicesViewModel =
                ViewModelProvider(this, devicesViewModelFactory).get(DeviceViewModel::class.java)
            devicesViewModel.getDevices("Bearer ${User.user.token}")

            devicesViewModel.responseLiveData.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    AddDeviceActivity.OPTIONS = response.body()?.toTypedArray()
                    val intent = Intent(this, AddDeviceActivity::class.java)
                    intent.putExtra("cultureId", culture.cultureId)
                    startActivity(intent)
                }
            })
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getDevices("Bearer ${User.user.token}")
        viewModel.responseLiveData.value?.body()?.filter {
            println("tu sam")
            culture.devices.contains(it)
        }
    }

    inner class DevicesAdapter(deviceViewModel: DeviceViewModel, listDevices: List<DeviceModel>) :
        RecyclerView.Adapter<DevicesAdapter.ViewHolder>() {

        private var listOfDevices: DeviceViewModel = deviceViewModel
        private var list = listDevices

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var deviceNameTextView: TextView? = null

            init {
                deviceNameTextView = itemView.findViewById(R.id.device_name_text_view)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val context = parent.context
            val inflater = LayoutInflater.from(context)
            val deviceListElement = inflater.inflate(R.layout.device_element_layout, parent, false)


            deviceListElement.setOnLongClickListener {
                val listOfDevicesView = findViewById<RecyclerView>(R.id.list_of_devices_view)
                var itemPosition = listOfDevicesView.getChildLayoutPosition(it)
                var task = listOfDevices.responseLiveData.value?.body()?.get(itemPosition)

                if (task != null) {
                    viewModel.deleteDeviceFromCulture(
                        "${User.user.token}",
                        culture.cultureId,
                        itemPosition.toLong()
                    )
                }
                true
            }

            return ViewHolder(deviceListElement)
        }

        override fun getItemCount(): Int {
            return if (listOfDevices.responseLiveData.value?.body() != null) {
                listOfDevices.responseLiveData.value?.body()!!.count()
            } else {
                0
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.deviceNameTextView?.text = list[position].devId
        }
    }

    private fun getDevicesForCulture(): List<DeviceModel> {
        val cultureViewModel: AgricultureViewModel =
            ViewModelProvider(
                this, agricultureViewModelFactory
            ).get(AgricultureViewModel::class.java)

        cultureViewModel.getCultures("${User.user.token}")
        val listOfCultureModel = cultureViewModel.responseLiveDataGetCultures.value?.body()

        if (listOfCultureModel != null) {
            for (model in listOfCultureModel) {
                if (model.cultureId.equals(culture.cultureId)) {
                    devices = model.devices
                    println(devices)
                }
            }
        } else devices = emptyList()

        return devices
    }

}
