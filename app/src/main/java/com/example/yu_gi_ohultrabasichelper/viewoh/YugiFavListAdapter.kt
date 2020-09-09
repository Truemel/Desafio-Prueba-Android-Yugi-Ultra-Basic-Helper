package com.example.yu_gi_ohultrabasichelper.viewoh

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.yu_gi_ohultrabasichelper.R
import com.example.yu_gi_ohultrabasichelper.extras.IMAGES_CARDS
import com.example.yu_gi_ohultrabasichelper.extras.JPG
import com.example.yu_gi_ohultrabasichelper.extras.fixNameToPath
import com.example.yu_gi_ohultrabasichelper.modeloh.room.YugiFavouriteTablePojo
import com.squareup.picasso.Picasso

class YugiFavListAdapter(var list:MutableList<YugiFavouriteTablePojo>, var context: Context, var onDelListener: OnDeleteListener):RecyclerView.Adapter<YugiFavListAdapter.Holder>(),
    View.OnClickListener {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var frame:ConstraintLayout
        lateinit var name:TextView
        lateinit var image:ImageView
        lateinit var del:Button
    }

    fun updateList(list: MutableList<YugiFavouriteTablePojo>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.favourite_list_adapter_layout, parent, false)
        val holder:Holder = Holder(view)
        holder.name = view.findViewById(R.id.card_name)
        holder.image = view.findViewById(R.id.image)
        holder.frame = view.findViewById(R.id.cLay)
        holder.frame.setOnClickListener(this)
        holder.del = view.findViewById(R.id.del_button)
        holder.del.setOnClickListener(this)

        return holder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.frame.tag = "FRAME $position"
        holder.del.tag = "DEL $position"
        holder.name.text = list[position].name
        Picasso.get().load(IMAGES_CARDS+ fixNameToPath(list[position].name)+ JPG).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onClick(v: View?) {
        val aux = v!!.tag.toString()
        if(aux.contains("FRAME")){

        }else if(aux.contains("DEL"))
            onDelListener.onDelete(list[aux.substring(aux.indexOf(' ')+1).toInt()])
    }

    interface OnDeleteListener{
        fun onDelete(card:YugiFavouriteTablePojo)
    }
}