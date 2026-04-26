package com.example.uas_pemrogamanseluler_42430024.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_pemrogamanseluler_42430024.R
import com.example.uas_pemrogamanseluler_42430024.model.Destination

class DestinationAdapter(
    private var list: List<Destination>,
    private val onItemClick: (Destination) -> Unit
) : RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEmoji: TextView = itemView.findViewById(R.id.tvEmoji)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        val tvDuration: TextView = itemView.findViewById(R.id.tvDuration)
        val tvRating: TextView = itemView.findViewById(R.id.tvRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destination, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvEmoji.text = item.emoji
        holder.tvName.text = item.name
        holder.tvCategory.text = item.category
        holder.tvDuration.text = "⏱ ${item.duration}"
        holder.tvRating.text = "⭐ ${item.rating}"
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount() = list.size

    fun updateData(newList: List<Destination>) {
        list = newList
        notifyDataSetChanged()
    }
}
