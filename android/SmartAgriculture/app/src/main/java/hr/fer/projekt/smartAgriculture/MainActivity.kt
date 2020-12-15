package hr.fer.projekt.smartAgriculture


import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import hr.fer.projekt.smartAgriculture.model.MeasurementModel
import hr.fer.projekt.smartAgriculture.net.RestFactory
import hr.fer.projekt.smartAgriculture.repository.Repository
import kotlinx.android.synthetic.main.activity_main_sliding.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var TAG = "MainActivity"
    private var mSlideViewPager: ViewPager? = null
    private var linearLayout: LinearLayout? = null
    private var slideAdapter: SlideAdapter? = null
    private var measurementsList: MutableList<MeasurementModel> = mutableListOf(
        ////GUI testing
        MeasurementModel(1L, "123", Date(System.currentTimeMillis() - 1000 * 60 * 60 * 3), 23.3f, 34.4f, 23f, 12f),
        MeasurementModel(1L, "123", Date(System.currentTimeMillis() - 1000 * 60 * 60 * 5), 63.3f, 24.4f, 27f, 13f),
        MeasurementModel(1L, "123", Date(System.currentTimeMillis() - 1000 * 60 * 60 * 2), 23.3f, 60.4f, 20f, 20f),
        MeasurementModel(1L, "123", Date(System.currentTimeMillis() - 1000 * 60 * 60 * 1), 33.3f, 50.4f, 19f, 15f),
        MeasurementModel(1L, "123", Date(System.currentTimeMillis() - 1000 * 60 * 60 * 7), 20.3f, 22.4f, 22f, 9f)
        /////////////
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sliding)

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
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getMeasurements()
        viewModel.responseLiveData.observe(this, Observer { response ->
            if (response.isSuccessful) {

                slideViewPager.visibility = View.VISIBLE
                progres_bar.visibility = View.GONE

                Log.d(TAG, response.body().toString())
                mSlideViewPager = findViewById(R.id.slideViewPager)
                lastMeasurementDate.text = "Last measurement ${response.body()?.maxBy { m -> m.time.time }?.time.toString()}"
                slideAdapter = SlideAdapter(this@MainActivity, response.body()!!.toMutableList())
                mSlideViewPager?.adapter = slideAdapter
            } else {
                Log.d(TAG, response.errorBody().toString())
            }
        })
    }

}
