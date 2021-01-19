package hr.fer.projekt.smartAgriculture.activities

import RegisterInActivity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import hr.fer.projekt.smartAgriculture.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register_in.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerButton = findViewById<Button>(R.id.register_in_main)
        val signInButton = findViewById<Button>(R.id.sign_in_main)

        signInButton.setOnClickListener {
            val intent: Intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            val intent: Intent = Intent(this, RegisterInActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

    }
    private fun removeErrorMessages() {
        login_error_text_view.visibility = View.GONE
        login_error_response.visibility = View.GONE
    }

}