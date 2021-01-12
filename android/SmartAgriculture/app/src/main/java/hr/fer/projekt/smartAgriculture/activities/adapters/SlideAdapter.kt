package hr.fer.projekt.smartAgriculture.activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.model.MeasurementModel
import java.util.*

class SlideAdapter(context: Context, measurementsList: MutableList<MeasurementModel>) : PagerAdapter() {

    private val context: Context = context
    private var layoutInflater: LayoutInflater? = null
    var currentTime = Calendar.getInstance().time
    var slideImages: Array<Int>
    var values: Array<String>
    var graphLineDatas: Array<LineData?>

    init {

        slideImages = arrayOf(
            R.drawable.temperature_icon,
            R.drawable.humidity_icon,
            R.drawable.soil_temperature_icon,
            R.drawable.soil_humidity_icon
        )

        measurementsList?.sortBy { measurementModel: MeasurementModel -> measurementModel.time.time }

        values = arrayOf(
            measurementsList?.get(measurementsList.size-1)?.airTemperature.toString() + "°",
            measurementsList?.get(measurementsList.size-1)?.airHumidity.toString() + "%",
            measurementsList?.get(measurementsList.size-1)?.soilTemperature.toString() + "°",
            measurementsList?.get(measurementsList.size-1)?.soilHumidity.toString() + "%"
        )

        graphLineDatas = initializeGraphs(measurementsList)
        /*
        //For testing GUI
        ///////////
        val entries = mutableListOf<Entry>()
        for (i in 1..10) {
            entries.add(Entry(i * 1f, (Math.random() * 6f).toFloat()))
        }

        val lineDataSet: LineDataSet = LineDataSet(entries, "Temperatures")
        lineDataSet.valueTextSize = 10f
        lineDataSet.setDrawFilled(true)

        var lineData  = LineData(lineDataSet)

        //graphLineDatas = arrayOf(lineData, lineData, lineData, lineData)
        /////////
        */

    }

    override fun getCount(): Int {
        return slideImages.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view: View? = layoutInflater?.inflate(R.layout.slide_layout, container, false)
        val slideImageView: ImageView? = view?.findViewById(R.id.slideImageView)
        val valueTextView: TextView? = view?.findViewById(R.id.valueTextView)
        val lineChart: LineChart? = view?.findViewById(R.id.lineChart)

        slideImageView?.setImageResource(slideImages[position])
        valueTextView?.text = values[position]
        lineChart?.data = graphLineDatas[position]
        lineChart?.invalidate()

        container.addView(view)

        return view as Any
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

    private fun initializeGraphs(measurementsList: MutableList<MeasurementModel>?) : Array<LineData?> {

        val airTemperatureEntries = mutableListOf<Entry>()
        if (measurementsList != null) {
            var i : Float = 1.0f
            for (m : MeasurementModel in measurementsList) {

                //returns difference in milliseconds => * 1000 to seconds, * 60 to minutes * 60 to hours
                airTemperatureEntries.add(Entry(/*abs(m.time.time - currentTime.time) * 1000 * 60 * 60.0f*/i++, m.airTemperature))
            }
        }

        val airHumidityEntries = mutableListOf<Entry>()
        if (measurementsList != null) {
            var i : Float = 1.0f
            for (m : MeasurementModel in measurementsList) {
                airHumidityEntries.add(Entry(/*abs(m.time.time - currentTime.time) * 1000 * 60 * 60.0f*/i++, m.airHumidity))
            }
        }

        val soilTemperatureEntries = mutableListOf<Entry>()
        if (measurementsList != null) {
            var i : Float = 1.0f
            for (m : MeasurementModel in measurementsList) {
                soilTemperatureEntries.add(Entry(/*abs(m.time.time - currentTime.time) * 1000 * 60 * 60.0f*/i++, m.soilTemperature))
            }
        }

        val soilHumidityEntries = mutableListOf<Entry>()
        if (measurementsList != null) {
            var i : Float = 1.0f
            for (m : MeasurementModel in measurementsList) {
                soilHumidityEntries.add(Entry(/*abs(m.time.time - currentTime.time) * 1000 * 60 * 60.0f*/i++, m.soilHumidity))
            }
        }

        val lineDateSetAirTemperature: LineDataSet = LineDataSet(airTemperatureEntries, "Temperatures")
        val lineDateSetAirHumidity: LineDataSet = LineDataSet(airHumidityEntries, "Humidity")
        val lineDateSetSoilTemperature: LineDataSet = LineDataSet(soilTemperatureEntries, "Temperatures")
        val lineDateSetSoilHumidity: LineDataSet = LineDataSet(soilHumidityEntries, "Humidity")

        lineDateSetAirTemperature.valueTextSize = 15f
        lineDateSetAirTemperature.setDrawFilled(true)

        lineDateSetAirHumidity.valueTextSize = 15f
        lineDateSetAirHumidity.setDrawFilled(true)

        lineDateSetSoilTemperature.valueTextSize = 15f
        lineDateSetSoilTemperature.setDrawFilled(true)

        lineDateSetSoilHumidity.valueTextSize = 15f
        lineDateSetSoilHumidity.setDrawFilled(true)

        return arrayOf(LineData(lineDateSetAirTemperature), LineData(lineDateSetAirHumidity), LineData(lineDateSetSoilTemperature), LineData(lineDateSetSoilHumidity))
    }
}