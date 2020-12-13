package hr.fer.projekt.smartAgriculture

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData

class SlideAdapter(context: Context, slideImages: Array<Int>, values: Array<String>, graphLineDatas: Array<LineData>) : PagerAdapter() {

    private val context: Context = context
    private var layoutInflater: LayoutInflater? = null

    public var slideImages: Array<Int> = slideImages//arrayOf(R.drawable.temperature_icon, R.drawable.humidity_icon, R.drawable.soil_temperature_icon, R.drawable.soil_humidity_icon)
    public var values: Array<String> = values//arrayOf()
    public var graphLineDatas: Array<LineData> = graphLineDatas//arrayOf()

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
        valueTextView?.setText(values[position])
        lineChart?.data = graphLineDatas[position]
        lineChart?.invalidate()

        container.addView(view)

        return view as Any
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}