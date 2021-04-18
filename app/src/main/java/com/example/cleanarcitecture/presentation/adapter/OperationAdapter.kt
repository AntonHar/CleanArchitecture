package com.example.cleanarcitecture.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarcitecture.R
import com.example.cleanarcitecture.domain.Operation
import com.example.cleanarcitecture.presentation.ui.MainFragment

class OperationAdapter internal constructor(
    private var data: MutableList<Operation>
) : RecyclerView.Adapter<OperationAdapter.ViewHolder>() {

    private var listener: ItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.operation_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = data[position]
        viewHolder.text.text = item.toString()
        viewHolder.itemView.setOnClickListener {
            listener?.onItemClick(item)
        }

    }

    override fun getItemCount() = data.size

    fun setData(data: MutableList<Operation>){
        this.data = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById<TextView>(R.id.operation_text)


    }

    fun setListener(itemClickListener: MainFragment?) {
        listener = itemClickListener
    }
}

interface ItemClickListener {
    fun onItemClick(operation: Operation)
}
