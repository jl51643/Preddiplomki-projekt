package hr.fer.projekt.smartAgriculture

import android.os.AsyncTask
import android.content.Intent
import android.os.AsyncTask.execute
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_register_in.*

class RegisterInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_in)

        val registerButton = findViewById<Button>(R.id.loginButton)
        registerButton.setOnClickListener {

            val intent = Intent(this@RegisterInActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
