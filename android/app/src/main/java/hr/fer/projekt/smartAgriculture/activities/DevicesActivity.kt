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
import hr.fer.projekt.smartAgriculture.DatabaseHandler
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.model.DeviceModel
import hr.fer.projekt.smartAgriculture.model.User
import hr.fer.projekt.smartAgriculture.repository.Repository
import hr.fer.projekt.smartAgriculture.viewModel.DeviceViewModel
import hr.fer.projekt.smartAgriculture.viewModel.TasksViewModel
import hr.fer.projekt.smartAgriculture.viewModel.factory.DeviceViewModelFactory
import hr.fer.projekt.smartAgriculture.viewModel.factory.TaskViewModelFactory
import kotlinx.android.synthetic.main.activity_devices.*

class DevicesActivity : AppCompatActivity() {

    val repository = Repository()
    val viewModelFactory = DeviceViewModelFactory(repository)
    lateinit var devicesAdapter: DevicesAdapter
    lateinit var viewModel: DeviceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices)

        val listOfDevicesView = findViewById<RecyclerView>(R.id.list_of_devices_view)
        val addNewDeviceButton = findViewById<FloatingActionButton>(R.id.addDevicesButton)

        listOfDevicesView.layoutManager = LinearLayoutManager(applicationContext)

        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.cell_divider
            )!!
        )
        listOfDevicesView.addItemDecoration(decorator)

        viewModel = ViewModelProvider(this, viewModelFactory).get(DeviceViewModel::class.java)
        devicesAdapter = DevicesAdapter(viewModel)
        listOfDevicesView.adapter = devicesAdapter

        viewModel.responseLiveData.observe(this, Observer {
            devicesAdapter.notifyDataSetChanged()
        })

        /*-----------------*/
        addNewDeviceButton.setOnClickListener {
            val intent = Intent(this, AddDeviceActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getDevices("Bearer ${User.user.token}")
    }

    inner class DevicesAdapter(deviceViewModel: DeviceViewModel) :
        RecyclerView.Adapter<DevicesAdapter.ViewHolder>() {

        private var listOfDevices: DeviceViewModel = deviceViewModel

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


            deviceListElement.setOnClickListener {

                val listOfDevicesView = findViewById<RecyclerView>(R.id.list_of_devices_view)
                var itemPosition = listOfDevicesView.getChildLayoutPosition(it)
                val device = listOfDevices.responseLiveData.value?.body()?.get(itemPosition)

                val intent = Intent(this@DevicesActivity, AddDeviceActivity::class.java).apply {
                   /* putExtra("device", device)*/
                    putExtra("position", itemPosition)
                }

                startActivity(intent)
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
            holder.deviceNameTextView?.text =
                listOfDevices.responseLiveData.value?.body()!![position].devId
        }
    }

}
