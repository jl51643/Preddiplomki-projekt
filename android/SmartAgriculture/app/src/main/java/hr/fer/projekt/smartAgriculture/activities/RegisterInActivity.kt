package hr.fer.projekt.smartAgriculture.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.veiwModel.RegisterViewModel
import hr.fer.projekt.smartAgriculture.veiwModel.RegisterViewModelFactory
import hr.fer.projekt.smartAgriculture.model.RegistrationModel
import hr.fer.projekt.smartAgriculture.model.TokenModel
import hr.fer.projekt.smartAgriculture.model.User
import hr.fer.projekt.smartAgriculture.repository.Repository
import kotlinx.android.synthetic.main.activity_register_in.*

class RegisterInActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_in)

        first_name.setOnFocusChangeListener{ v, hasFocus -> removeErrorMessages() }
        last_name.setOnFocusChangeListener{ v, hasFocus -> removeErrorMessages() }
        email.setOnFocusChangeListener{ v, hasFocus -> removeErrorMessages() }
        username.setOnFocusChangeListener{ v, hasFocus -> removeErrorMessages() }
        password.setOnFocusChangeListener{ v, hasFocus -> removeErrorMessages() }

        val registerButton = findViewById<Button>(R.id.register_in_button)
        registerButton.setOnClickListener {


            val repository = Repository()
            val viewModelFactory = RegisterViewModelFactory(repository)

            viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)
            val registrationModel = RegistrationModel(username.text.toString(), password.text.toString(), email.text.toString())
            viewModel.registerUser(registrationModel)

            viewModel.responseLiveData.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    User.user.token = response.body()?.token.toString()
                    User.user.username = response.body()?.username.toString()
                    val intent = Intent(this@RegisterInActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    login_error_text_view.visibility = View.VISIBLE
                    login_error_response.text = "${response.message()} ${response.code()}"
                    login_error_response.visibility = View.VISIBLE
                }
            })

        }
    }

    private fun removeErrorMessages() {
        login_error_text_view.visibility = View.GONE
        login_error_response.visibility = View.GONE
    }
}
