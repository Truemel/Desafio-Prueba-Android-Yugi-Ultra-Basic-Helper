package com.example.yu_gi_ohultrabasichelper.viewoh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yu_gi_ohultrabasichelper.R

class YugiSetsAdapter(var list: MutableList<String>):RecyclerView.Adapter<YugiSetsAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var text: TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.set_list_adapter_layout, parent, false)
        val holder:Holder = Holder(view)
        holder.text = view.findViewById(R.id.set_name)

        return holder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.text.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
    
    fun updateList(list: MutableList<String>){
        this.list = list
        notifyDataSetChanged()
    }
}