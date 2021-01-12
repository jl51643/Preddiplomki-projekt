package hr.fer.projekt.smartAgriculture.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.model.TokenModel
import hr.fer.projekt.smartAgriculture.model.LoginModel
import hr.fer.projekt.smartAgriculture.model.User
import hr.fer.projekt.smartAgriculture.repository.Repository
import hr.fer.projekt.smartAgriculture.veiwModel.LoginViewModel
import hr.fer.projekt.smartAgriculture.veiwModel.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        username.setOnFocusChangeListener { v, hasFocus -> removeErrorMessages()}

        password.setOnFocusChangeListener { v, hasFocus -> removeErrorMessages()}

        register_in_link.setOnClickListener {
            val intent: Intent = Intent(this, RegisterInActivity::class.java)
            startActivity(intent)
        }

        sign_in_button.setOnClickListener {

            loading.visibility = View.VISIBLE

            val repository = Repository()
            val viewModelFactory = LoginViewModelFactory(repository)

            viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
            val loginModel = LoginModel(username.text.toString(), password.text.toString())
            viewModel.login(loginModel)

            viewModel.responseLiveData.observe(this, Observer { response ->
                loading.visibility = View.GONE
                if (response.isSuccessful) {
                    User.user.token = response.body()?.token.toString()
                    User.user.username = response.body()?.username.toString()
                    val intent = Intent(this@LogInActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    login_error_text_view.visibility = View.VISIBLE
                    login_error_response.text = "${response.message()}  ${response.code()}"
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
