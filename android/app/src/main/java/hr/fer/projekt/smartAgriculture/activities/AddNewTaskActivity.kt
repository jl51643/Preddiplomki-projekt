package hr.fer.projekt.smartAgriculture.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import hr.fer.projekt.smartAgriculture.DatabaseHandler
import hr.fer.projekt.smartAgriculture.R
import hr.fer.projekt.smartAgriculture.model.TaskModel
import hr.fer.projekt.smartAgriculture.model.User
import hr.fer.projekt.smartAgriculture.viewModel.TasksViewModel
import hr.fer.projekt.smartAgriculture.viewModel.factory.TaskViewModelFactory
import kotlinx.android.synthetic.main.activity_add_new_task.*

class AddNewTaskActivity : AppCompatActivity() {

    private lateinit var viewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_task)

        /* val viewModel =
             ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
                 .get(TasksViewModel::class.java)*/

        var task: TaskModel? = null
        if (intent.hasExtra("task")) {
            task = intent.getSerializableExtra("task") as TaskModel
            notification_text_text_view.setText(task.task)
        }

        if (intent.hasExtra("message")) {
            notification_text_text_view.setText(intent.getStringExtra("message"))
        }

        val update: Boolean = task != null

        save_new_task.setOnClickListener {
            val databaseHandler = DatabaseHandler(applicationContext)
            val viewModelFactory = TaskViewModelFactory(databaseHandler)

            viewModel = ViewModelProvider(this, viewModelFactory).get(TasksViewModel::class.java)
            val taskModel = TaskModel(
                0,
                task = notification_text_text_view.text.toString(),
                user = User.user.username
            )
            if (update) {
                taskModel.taskId = task!!.taskId
                viewModel.updateTask(taskModel)
            } else {
                viewModel.addTask(taskModel)
            }

            finish()
        }
    }
}
