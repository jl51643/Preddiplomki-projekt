package hr.fer.projekt.smartAgriculture


import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import hr.fer.projekt.smartAgriculture.model.MeasurementModel
import hr.fer.projekt.smartAgriculture.net.RestFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_sliding.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mSlideViewPager: ViewPager? = null
    private var linerarLayout: LinearLayout? = null
    private var slideAdapter: SlideAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sliding)

        val entries = mutableListOf<Entry>()
        for (i in 1..10) {
            entries.add(Entry(i*1f, (Math.random()*6f).toFloat()))
        }

        val lineDataSet : LineDataSet = LineDataSet(entries, "Temperatures")
        //lineDataSet.setColor(255, 1)
        lineDataSet.valueTextSize = 10f
        //lineDataSet.valueTextColor = 100
        //lineDataSet.setCircleColor(255)
        lineDataSet.setDrawFilled(true)

        //lineDataSet.setDrawValues(true)


        val lineData : LineData = LineData(lineDataSet)
        /*lineChart.setData(lineData)
        lineChart.invalidate()*/

        mSlideViewPager = findViewById(R.id.slideViewPager)
        linerarLayout = findViewById(R.id.linearLayout)

        slideAdapter = SlideAdapter(this,
            arrayOf(R.drawable.temperature_icon, R.drawable.humidity_icon, R.drawable.soil_temperature_icon, R.drawable.soil_humidity_icon),
            arrayOf("3°", "53%", "-2°", "32%"),
            arrayOf(lineData, lineData, lineData, lineData))
        mSlideViewPager?.adapter = slideAdapter



    }

    private inner class LoadLatestMeasurements: AsyncTask<Void, Void, List<MeasurementModel>?>() {
        override fun doInBackground(vararg params: Void?): List<MeasurementModel>? {
            val rest = RestFactory.instance

            return rest.getAllMeasurements()
        }

    }


}
