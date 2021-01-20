package hr.fer.projekt.smartAgriculture.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.model.User
import hr.fer.projekt.smartAgriculture.repository.Repository
import hr.fer.projekt.smartAgriculture.viewModel.AgricultureViewModel
import hr.fer.projekt.smartAgriculture.viewModel.DeviceViewModel
import hr.fer.projekt.smartAgriculture.viewModel.factory.AgricultureViewModelFactory
import hr.fer.projekt.smartAgriculture.viewModel.factory.DeviceViewModelFactory

class AgriculturesListActivity : AppCompatActivity() {

    lateinit var viewModel: AgricultureViewModel
    lateinit var culturesAdapter: CulturesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agricultures_list_activity)

        val listOfAgriculturesView = findViewById<RecyclerView>(R.id.listOfAgriculturesView)
        val newAgricultureActionButton =
            findViewById<FloatingActionButton>(R.id.newAgricultureActionButton)

        listOfAgriculturesView.layoutManager = LinearLayoutManager(applicationContext)

        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.cell_divider
            )!!
        )
        listOfAgriculturesView.addItemDecoration(decorator)

        val repository = Repository()
        val viewModelFactory = AgricultureViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(AgricultureViewModel::class.java)
        culturesAdapter = CulturesAdapter(viewModel)
        listOfAgriculturesView.adapter = culturesAdapter

        viewModel.responseLiveDataGetCultures.observe(this, Observer {
            culturesAdapter.notifyDataSetChanged()
        })

        newAgricultureActionButton.setOnClickListener {
            val devicesViewModelFactory = DeviceViewModelFactory(repository)
            val devicesViewModel =
                ViewModelProvider(this, devicesViewModelFactory).get(DeviceViewModel::class.java)
            devicesViewModel.getDevices("Bearer ${User.user.token}")
            devicesViewModel.responseLiveData.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    AgricultureDetailsActivity.OPTIONS = response.body()?.toTypedArray()
                    val intent = Intent(this, AgricultureDetailsActivity::class.java)
                    startActivity(intent)
                }
            })

        }

        val myTasksButton = findViewById<Button>(R.id.MyTasksButton)

        myTasksButton.setOnClickListener {
            startActivity(Intent(applicationContext, TasksActivity::class.java))
        }

        val notificationsButton = findViewById<Button>(R.id.NotificationsButton)
        notificationsButton.setOnClickListener {
            startActivity(Intent(applicationContext, NotificationsActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getCultures("Bearer ${User.user.token}")
    }

    inner class CulturesAdapter(listOfCulturesViewModel: AgricultureViewModel) :
        RecyclerView.Adapter<CulturesAdapter.ViewHolder>() {

        private var listOfCultures: AgricultureViewModel = listOfCulturesViewModel

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var cultureTitleTextView: TextView? = null
            //var cultureDescriptionTextView: TextView? = null

            init {
                cultureTitleTextView = itemView.findViewById(R.id.noteTitleTextView)
                //cultureDescriptionTextView = itemView.findViewById(R.id.culture_description)
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int
        ): CulturesAdapter.ViewHolder {

            val context = parent.context
            val inflater = LayoutInflater.from(context)
            val agricultureListElement =
                inflater.inflate(R.layout.agriculture_list_element, parent, false)

            val listOfAgriculturesView = findViewById<RecyclerView>(R.id.listOfAgriculturesView)

            agricultureListElement.setOnClickListener {

                var itemPosition = listOfAgriculturesView.getChildLayoutPosition(it)
                val culture =
                    listOfCultures.responseLiveDataGetCultures.value?.body()?.get(itemPosition)
                startActivity(
                    Intent(
                        this@AgriculturesListActivity,
                        AgricultureDisplay::class.java
                    ).putExtra("culture", culture).putExtra("position", itemPosition)
                )
                culturesAdapter.notifyDataSetChanged()

            }

            agricultureListElement.setOnLongClickListener {
                var itemPosition = listOfAgriculturesView.getChildLayoutPosition(it)
                var culture =
                    listOfCultures.responseLiveDataGetCultures.value?.body()?.get(itemPosition)

                if (culture != null) {
                    viewModel.deleteCulture("Bearer ${User.user.token}", culture.cultureId)
                    culturesAdapter.notifyItemRemoved(itemPosition)
                }

                true
            }

            return ViewHolder(agricultureListElement)
        }

        override fun getItemCount(): Int {
            return if (listOfCultures.responseLiveDataGetCultures.value?.body() != null) {
                listOfCultures.responseLiveDataGetCultures.value?.body()!!.count()
            } else {
                0
            }
        }

        override fun onBindViewHolder(holder: CulturesAdapter.ViewHolder, position: Int) {
            holder.cultureTitleTextView?.text =
                listOfCultures.responseLiveDataGetCultures.value?.body()!![position].title
        }

    }

}

