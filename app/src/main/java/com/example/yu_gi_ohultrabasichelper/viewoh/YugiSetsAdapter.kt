package com.example.yu_gi_ohultrabasichelper.viewoh

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yu_gi_ohultrabasichelper.R
import com.example.yu_gi_ohultrabasichelper.extras.getSetImagePath
import com.example.yu_gi_ohultrabasichelper.modeloh.room.YugiSetTablePojo
import com.squareup.picasso.Picasso

class YugiSetsAdapter(var list: MutableList<YugiSetTablePojo>, var context: Context):RecyclerView.Adapter<YugiSetsAdapter.Holder>(),
    View.OnClickListener {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var image:ImageView
        lateinit var text: TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.set_list_adapter_layout, parent, false)
        val holder:Holder = Holder(view)
        holder.text = view.findViewById(R.id.set_name)
        holder.image = view.findViewById(R.id.image)
        holder.itemView.setOnClickListener(this)

        return holder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.tag = list[position].name
        Picasso.get().load(getSetImagePath(list[position].name)).into(holder.image)
        holder.text.text = list[position].name
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: MutableList<YugiSetTablePojo>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        (context as MainActivity).changeFragment(YugiCardsListFragment(), v!!.tag.toString())
    }
}