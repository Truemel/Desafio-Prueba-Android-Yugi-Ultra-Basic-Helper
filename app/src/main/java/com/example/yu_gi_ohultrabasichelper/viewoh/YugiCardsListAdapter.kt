package com.example.yu_gi_ohultrabasichelper.viewoh

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yu_gi_ohultrabasichelper.R
import com.example.yu_gi_ohultrabasichelper.extras.getCardImagePath
import com.example.yu_gi_ohultrabasichelper.modeloh.room.YugiCardTablePojo
import com.squareup.picasso.Picasso

class YugiCardsListAdapter(var list: MutableList<YugiCardTablePojo>, var context: Context):RecyclerView.Adapter<YugiCardsListAdapter.Holder>(),
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
        holder.itemView.tag = list[position].name
        Picasso.get().load(getCardImagePath(list[position].name)).into(holder.image)
        holder.text.text = list[position].name
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: MutableList<YugiCardTablePojo>){
        this.list = list
        Log.i("FAIL", list.toString())
        Log.i("FAIL 2", this.list.toString())
        notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        val dialog:YugiCardDataFragmentDialog = YugiCardDataFragmentDialog()
        dialog.showNow((context as MainActivity).supportFragmentManager, v!!.tag.toString())
    }
}