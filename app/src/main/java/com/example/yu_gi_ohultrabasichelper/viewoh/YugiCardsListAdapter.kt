package com.example.yu_gi_ohultrabasichelper.viewoh

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yu_gi_ohultrabasichelper.R
import com.example.yu_gi_ohultrabasichelper.extras.IMAGES_CARDS
import com.example.yu_gi_ohultrabasichelper.extras.JPG
import com.example.yu_gi_ohultrabasichelper.extras.fixNameToPath
import com.example.yu_gi_ohultrabasichelper.modeloh.retrofit.CardsRetro
import com.squareup.picasso.Picasso

class YugiCardsListAdapter(var list: MutableList<CardsRetro>, var context: Context):RecyclerView.Adapter<YugiCardsListAdapter.Holder>(),
    View.OnClickListener {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var text:TextView
        lateinit var image:ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.set_list_adapter_layout, parent, false)
        val holder:Holder = Holder(view)
        holder.image = view.findViewById(R.id.image)
        holder.text = view.findViewById(R.id.set_name)
        holder.itemView.setOnClickListener(this)

        return holder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.tag = position
        Picasso.get().load(IMAGES_CARDS+ fixNameToPath(list[position].name)+JPG).into(holder.image)
        holder.text.text = list[position].name
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: MutableList<CardsRetro>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        //(context as MainActivity).changeFragment()
    }
}