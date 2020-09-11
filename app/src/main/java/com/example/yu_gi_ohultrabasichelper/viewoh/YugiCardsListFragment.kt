package com.example.yu_gi_ohultrabasichelper.viewoh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yu_gi_ohultrabasichelper.R
import com.example.yu_gi_ohultrabasichelper.viewmodeloh.YugiViewModel

class YugiCardsListFragment: Fragment() {

    private lateinit var list:RecyclerView
    private lateinit var adapter: YugiCardsListAdapter
    private lateinit var vModel:YugiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = YugiCardsListAdapter(mutableListOf(), context as MainActivity)
        vModel = ViewModelProvider(this).get(YugiViewModel::class.java)
        vModel.setCardList(tag!!)
        vModel.yugiCardsList.observe(context as MainActivity, Observer { adapter.updateList(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.recycler_list_layout, container, false)
        val text:TextView = view.findViewById(R.id.list_title)
        text.text = "Card List"
        list = view.findViewById(R.id.recycler_list)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter

        return view
    }
}