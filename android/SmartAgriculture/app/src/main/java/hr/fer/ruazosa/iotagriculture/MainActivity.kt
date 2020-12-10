package hr.fer.ruazosa.iotagriculture


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val lineChart = findViewById<LineChart>(R.id.chart)

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
        lineChart.setData(lineData)
        lineChart.invalidate()
    }

}
