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
import kotlin.math.*

class SlideAdapter(context: Context, measurementsList: MutableList<MeasurementModel>) : PagerAdapter() {

    private val context: Context = context
    private var layoutInflater: LayoutInflater? = null
    var currentTime: Date = Calendar.getInstance().time
    var slideImages: Array<Int> = arrayOf(
        R.drawable.temperature_icon,
        R.drawable.humidity_icon,
        R.drawable.ic_pressure,
        R.drawable.ic_luminsity
    )
    var values: Array<String>
    var graphLineDatas: Array<LineData?>

    init {

        measurementsList?.sortBy { measurementModel: MeasurementModel -> measurementModel.time.time }

        values = arrayOf(
            measurementsList?.get(measurementsList.size-1)?.airTemperature.toString() + "°",
            measurementsList?.get(measurementsList.size-1)?.airHumidity.toString() + "%",
            measurementsList?.get(measurementsList.size-1)?.pressure.toString() + "kPa",
            measurementsList?.get(measurementsList.size-1)?.luminosity.toString() + "lx"
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
                airTemperatureEntries.add(Entry(
                    abs(m.time.time - currentTime.time) * 1000 * 60 * 60.0f/*i++*/,
                    m.airTemperature))
            }
        }

        val airHumidityEntries = mutableListOf<Entry>()
        if (measurementsList != null) {
            var i : Float = 1.0f
            for (m : MeasurementModel in measurementsList) {
                airHumidityEntries.add(Entry(
                    abs(m.time.time - currentTime.time) * 1000 * 60 * 60.0f/*i++*/,
                    m.airHumidity))
            }
        }

        val pressureEntries = mutableListOf<Entry>()
        if (measurementsList != null) {
            var i : Float = 1.0f
            for (m : MeasurementModel in measurementsList) {
                pressureEntries.add(Entry(
                    abs(m.time.time - currentTime.time) * 1000 * 60 * 60.0f/*i++*/,
                    m.pressure.toFloat()
                ))
            }
        }

        val luminosity = mutableListOf<Entry>()
        if (measurementsList != null) {
            var i : Float = 1.0f
            for (m : MeasurementModel in measurementsList) {
                luminosity.add(Entry(
                    abs(m.time.time - currentTime.time) * 1000 * 60 * 60.0f/*i++*/,
                    m.luminosity.toFloat()
                ))
            }
        }

        val lineDateSetAirTemperature: LineDataSet = LineDataSet(airTemperatureEntries, "Temperatures")
        val lineDateSetAirHumidity: LineDataSet = LineDataSet(airHumidityEntries, "Humidity")
        val lineDateSetPressure: LineDataSet = LineDataSet(pressureEntries, "Pressure")
        val lineDateSetLuminosity: LineDataSet = LineDataSet(luminosity, "Luminosity")

        lineDateSetAirTemperature.valueTextSize = 15f
        lineDateSetAirTemperature.setDrawFilled(true)

        lineDateSetAirHumidity.valueTextSize = 15f
        lineDateSetAirHumidity.setDrawFilled(true)

        lineDateSetPressure.valueTextSize = 15f
        lineDateSetPressure.setDrawFilled(true)

        lineDateSetLuminosity.valueTextSize = 15f
        lineDateSetLuminosity.setDrawFilled(true)

        return arrayOf(LineData(lineDateSetAirTemperature), LineData(lineDateSetAirHumidity), LineData(lineDateSetPressure), LineData(lineDateSetLuminosity))
    }
}