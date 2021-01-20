package hr.fer.projekt.smartAgriculture.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.model.CultureModel


class AgricultureDisplay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agriculture_display)

        val measurementButton: Button = findViewById(R.id.measurements_button)
        val devicesButton: Button = findViewById(R.id.devices_button)
        val notificationBell: ImageView = findViewById(R.id.notification_bell)
        val title: TextView = findViewById(R.id.agriculture_title)
        val description: TextView = findViewById(R.id.agriculture_description)

        val culture: CultureModel = intent.getSerializableExtra("culture") as CultureModel
        val position: Int = intent.getIntExtra("position", 0)

        title.text = culture.title
        description.text = culture.description

        measurementButton.setOnClickListener {
            val intent: Intent = Intent(this, MeasurementsActivity::class.java)
            intent.putExtra("culture", culture)
            startActivity(intent)
        }

        devicesButton.setOnClickListener {
            val intent: Intent = Intent(this, DevicesActivity::class.java)
            intent.putExtra("culture", culture)
            startActivity(intent)
        }

        notificationBell.setOnClickListener{
            val intent: Intent = Intent(this, SetCultureBoundaries::class.java)
            intent.putExtra("cultureId", culture.cultureId)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
    }
}