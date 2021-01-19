package hr.fer.projekt.smartAgriculture

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.fer.projekt.smartAgriculture.activities.MeasurementsActivity
import hr.fer.projekt.smartAgriculture.model.CultureModel

class AgricultureAdapter(listOfAgriculture: List<CultureModel>, context: Context) : RecyclerView.Adapter<AgricultureAdapter.ViewHolder>() {

    var listOfAgricultures: List<CultureModel> = listOfAgriculture
    var context: Context = context

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var agricultureTitleTextView: TextView? =
            itemView.findViewById(R.id.agricultureTitleEditText)

        /*   val item = object : SwipeToDeleteItem(this, 0, ItemTouchHelper.LEFT){
               override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                   *//* adapter.delete(viewHolder.adapterPosition)*//*

            }
        }
        val itemTouchHelper = ItemTouchHelper(item)
        itemTouchHelper.attach*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.agriculture_list_element, parent, false)
        )


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MeasurementsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listOfAgricultures.size


}
