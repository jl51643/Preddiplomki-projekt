package hr.fer.projekt.smartAgriculture.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.services.NotificationService
import kotlinx.android.synthetic.main.activity_notifications.*

class NotificationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        start_button.setOnClickListener {
            NotificationService.KEEP_RUNNING = true
            Intent(this, NotificationService::class.java).also { intent ->
                startService(intent)
            }
            //finish()
        }

        stop_button.setOnClickListener {
            NotificationService.KEEP_RUNNING = false
            //finish()
        }
    }
}
