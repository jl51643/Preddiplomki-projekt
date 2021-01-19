package hr.fer.projekt.smartAgriculture.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import hr.fer.projekt.smartAgriculture.AgricultureAdapter
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.viewModel.AgricultureViewModel
import kotlinx.android.synthetic.main.agricultures_list_activity.*

class AgriculturesListActivity : AppCompatActivity() {

    lateinit var viewModel: AgricultureViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agricultures_list_activity)

        listOfAgriculturesView.layoutManager = LinearLayoutManager(applicationContext)

        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)

        decorator.setDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.cell_divider
            )!!
        )

        listOfAgriculturesView.addItemDecoration(decorator)

        newAgricultureActionButton.setOnClickListener {
            val intent = Intent(this, AgricultureDetailsActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
    }


}

