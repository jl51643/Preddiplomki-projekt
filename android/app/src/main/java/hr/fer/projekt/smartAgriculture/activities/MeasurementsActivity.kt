package hr.fer.projekt.smartAgriculture.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.activities.adapters.SlideAdapter
import hr.fer.projekt.smartAgriculture.model.MeasurementModel
import hr.fer.projekt.smartAgriculture.model.User
import hr.fer.projekt.smartAgriculture.repository.Repository
import hr.fer.projekt.smartAgriculture.viewModel.MeasurementsViewModel
import hr.fer.projekt.smartAgriculture.viewModel.factory.MeasurementsViewModelFactory

class MeasurementsActivity : AppCompatActivity() {

    val slideViewPager = findViewById<TextView>(R.id.slideViewPager)
    val progres_bar = findViewById<ProgressBar>(R.id.progres_bar)
    val lastMeasurementDate = findViewById<TextView>(R.id.lastMeasurementDate)

    private lateinit var viewModel: MeasurementsViewModel
    private var TAG = "MainActivity"
    private var mSlideViewPager: ViewPager? = null
    private var linearLayout: LinearLayout? = null
    private var slideAdapter: SlideAdapter? = null

    ////GUI testing
    private var measurementsList: MutableList<MeasurementModel> = mutableListOf(

        /* MeasurementModel(1L, "123", Date(System.currentTimeMillis() - 1000 * 60 * 60 * 3), 23.3f, 34.4f, 23f, 12f),
         MeasurementModel(1L, "123", Date(System.currentTimeMillis() - 1000 * 60 * 60 * 5), 63.3f, 24.4f, 27f, 13f),
         MeasurementModel(1L, "123", Date(System.currentTimeMillis() - 1000 * 60 * 60 * 2), 23.3f, 60.4f, 20f, 20f),
         MeasurementModel(1L, "123", Date(System.currentTimeMillis() - 1000 * 60 * 60 * 1), 33.3f, 50.4f, 19f, 15f),
         MeasurementModel(1L, "123", Date(System.currentTimeMillis() - 1000 * 60 * 60 * 7), 20.3f, 22.4f, 22f, 9f)*/

    )
    /////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measurements_sliding)

        getLatestMeasurements()

        /* mSlideViewPager = findViewById(R.id.slideViewPager)
         lastMeasurementDate.text = "Last measurement ${measurementsList.maxBy { m -> m.time.time }?.time.toString()}"
         slideAdapter = SlideAdapter(this, measurementsList)
         mSlideViewPager?.adapter = slideAdapter
 */

    }

    private fun getLatestMeasurements() {
        slideViewPager.visibility = View.INVISIBLE
        progres_bar.visibility = View.VISIBLE

        val repository = Repository()
        val viewModelFactory = MeasurementsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MeasurementsViewModel::class.java)
        viewModel.getMeasurements("Bearer ${User.user.token}")
        viewModel.responseLiveData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                slideViewPager.visibility = View.VISIBLE
                progres_bar.visibility = View.GONE

                Log.d(TAG, response.body().toString())

                mSlideViewPager = findViewById(R.id.slideViewPager)
                lastMeasurementDate.text =
                    R.string.last_measurement_text.toString() + " " + response.body()
                        ?.maxBy { m -> m.time.time }?.time.toString()
                slideAdapter =
                    SlideAdapter(this@MeasurementsActivity, response.body()!!.toMutableList())
                mSlideViewPager?.adapter = slideAdapter
            } else {
                Log.d(TAG, response.errorBody().toString())
            }
        })
    }
}
