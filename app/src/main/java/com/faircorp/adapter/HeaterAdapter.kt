package com.faircorp.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.faircorp.OnHeaterSelectedListener
import com.faircorp.R
import com.faircorp.model.HeaterDto
class HeaterAdapter(val listener: OnHeaterSelectedListener) : RecyclerView.Adapter<HeaterAdapter.HeaterViewHolder>() { // (1)

    inner class HeaterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.heater_name)
        val room: TextView = view.findViewById(R.id.txt_heater_room)
        val status: TextView = view.findViewById(R.id.txt_status)
    }

    private val items = mutableListOf<HeaterDto>()

    fun update(heaters: List<HeaterDto>) {  // (4)
        items.clear()
        items.addAll(heaters)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size // (5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaterViewHolder { // (6)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_heaters_item, parent, false)
        return HeaterViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaterViewHolder, position: Int) {  // (7)
        val heater = items[position]
        holder.apply {
            name.text = heater.name
            status.text = heater.heaterStatus.toString()
            itemView.setOnClickListener { listener.onHeaterSelected(heater.id) }
        }
    }

    override fun onViewRecycled(holder: HeaterViewHolder) { // (2)
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}