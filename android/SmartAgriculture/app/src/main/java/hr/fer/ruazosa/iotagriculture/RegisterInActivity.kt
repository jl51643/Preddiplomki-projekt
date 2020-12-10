package hr.fer.ruazosa.iotagriculture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

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
