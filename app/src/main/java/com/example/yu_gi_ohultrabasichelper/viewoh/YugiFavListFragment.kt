package com.example.yu_gi_ohultrabasichelper.viewoh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yu_gi_ohultrabasichelper.R
import com.example.yu_gi_ohultrabasichelper.modeloh.room.YugiFavouriteTablePojo
import com.example.yu_gi_ohultrabasichelper.viewmodeloh.YugiViewModel
import com.google.android.material.button.MaterialButton

class YugiFavListFragment:Fragment(), YugiFavListAdapter.OnDeleteListener, View.OnClickListener {

    private lateinit var list:RecyclerView
    private lateinit var vModel:YugiViewModel
    private lateinit var adapter:YugiFavListAdapter
    lateinit var noFav:TextView
    lateinit var delAll:MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = YugiFavListAdapter(mutableListOf(), context as MainActivity, this)
        vModel = ViewModelProvider(this).get(YugiViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fav_list_layout, container, false)
        val text:TextView = view.findViewById(R.id.list_title)
        text.text = "Favourite List"
        noFav = view.findViewById(R.id.no_fav_text)
        list = view.findViewById(R.id.recycler_list)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter
        delAll = view.findViewById(R.id.del_all_button)
        delAll.setOnClickListener(this)

        vModel.yugiFavList.observe(context as MainActivity, Observer { adapter.updateList(it)
            if(adapter.itemCount == 0){
                noFav.visibility = View.VISIBLE
                list.visibility = View.GONE
            }else{
                noFav.visibility = View.GONE
                list.visibility = View.VISIBLE
            }})

        return view
    }

    override fun onDelete(card: YugiFavouriteTablePojo) {
        vModel.deleteFavCard(card)
        Toast.makeText(context, "Card deleted from favourites", Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {
        vModel.deleteAllFavCard()
        Toast.makeText(context, "Favourite list deleted", Toast.LENGTH_LONG).show()
    }
}