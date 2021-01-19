package hr.fer.projekt.smartAgriculture.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.model.BoundaryModel
import hr.fer.projekt.smartAgriculture.model.User
import hr.fer.projekt.smartAgriculture.repository.Repository
import hr.fer.projekt.smartAgriculture.viewModel.CultureBoundariesViewModel
import hr.fer.projekt.smartAgriculture.viewModel.factory.CultureBoundariesViewModelFactory
import kotlinx.android.synthetic.main.activity_set_culture_boundaries.*

class SetCultureBoundaries : AppCompatActivity() {

    lateinit var viewModel: CultureBoundariesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_culture_boundaries)

        val cultureId = intent.getLongExtra("cultureId", 0)

        save_new_culture_boundary.setOnClickListener {
            val newBoundaries = BoundaryModel(
                cultureId = cultureId,
                minAirTemperature = min_air_tem_edit_text.text.toString().toDouble(),
                maxAirTemperature = max_air_tem_edit_text.text.toString().toDouble(),
                minSoilTemperature = min_soil_temp_edit_text.text.toString().toDouble(),
                maxSoilTemperature = max_soil_temp_edit_text.text.toString().toDouble(),
                minAirHumidity = min_air_humidity_edit_text.text.toString().toDouble(),
                maxAirHumidity = max_air_humidity_edit_text.text.toString().toDouble(),
                minSoilHumidity = min_soil_humidity_edit_text.text.toString().toDouble(),
                maxSoilHumidity = max_soil_humidity_edit_text.text.toString().toDouble(),
                minPressure = min_pressure_edit_text.text.toString().toDouble(),
                maxPressure = max_pressure_edit_text.text.toString().toDouble(),
                minLuminosity = min_luminosity_edit_text.text.toString().toDouble(),
                maxLuminosity = max_luminosity_edit_text.text.toString().toDouble()
            )

            val repository = Repository()
            val viewModelFactory = CultureBoundariesViewModelFactory(repository)
            viewModel = ViewModelProvider(
                this,
                viewModelFactory
            ).get(CultureBoundariesViewModel::class.java)
            viewModel.addBoundary("Bearer ${User.user.token}", newBoundaries)

            viewModel.responseLiveData.observe(this, Observer {
            })

            finish()
        }
    }
}
