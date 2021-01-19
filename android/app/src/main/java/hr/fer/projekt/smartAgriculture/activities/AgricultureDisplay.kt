package hr.fer.projekt.smartAgriculture.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import hr.fer.projekt.smartAgriculture.AgricultureAdapter
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.viewModel.AgricultureViewModel


class AgricultureDisplay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agriculture_display)

        val measurementButton: Button = findViewById(R.id.measurements_button)
        val devicesButton: Button = findViewById(R.id.devices_button)

        measurementButton.setOnClickListener {
            val intent: Intent = Intent(this, MeasurementsActivity::class.java)
            startActivity(intent)
        }

        devicesButton.setOnClickListener {
            val intent: Intent = Intent(this, DevicesActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
    }
}