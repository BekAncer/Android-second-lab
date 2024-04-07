package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ViewAdapter(private var data: List<CatModel>) : RecyclerView.Adapter<ViewAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView1: TextView = view.findViewById(R.id.textView1)
        val textView2: TextView = view.findViewById(R.id.textView2)
        val textView3: TextView = view.findViewById(R.id.textView3)
        val textView4: TextView = view.findViewById(R.id.textView4)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = data[position]
        with(holder) {
            textView1.text = dataItem.name
            textView2.text = "Health: ${dataItem.health}"
            textView3.text = "Grooming: ${dataItem.grooming}"
            textView4.text = "Shedding: ${dataItem.shedding}"
            Picasso.get().load(dataItem.image).into(imageView)
        }
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<CatModel>) {
        data = newData
        notifyDataSetChanged()
    }
}