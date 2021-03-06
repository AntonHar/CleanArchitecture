package com.example.cleanarcitecture.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarcitecture.R
import com.example.cleanarcitecture.domain.entity.Person
import com.example.cleanarcitecture.presentation.ui.MainFragment

class PersonAdapter internal constructor(
    private var persons: List<Person>
) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private var listener: ItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.person_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val person = persons[position]
        viewHolder.text.text = person.toString()
        viewHolder.itemView.setOnClickListener {
            listener?.onItemClick(person)
        }

    }

    override fun getItemCount() = persons.size

    fun setData(data: List<Person>) {
        this.persons = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: View = view.findViewById(R.id.person_container)
        val text: TextView = view.findViewById<TextView>(R.id.person_text)
    }

    fun setListener(itemClickListener: MainFragment?) {
        listener = itemClickListener
    }
}


