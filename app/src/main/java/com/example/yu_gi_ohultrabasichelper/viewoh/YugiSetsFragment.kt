package com.example.yu_gi_ohultrabasichelper.viewoh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yu_gi_ohultrabasichelper.R

class YugiSetsFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.recycler_list_layout, container, false)
        val texto:TextView = view.findViewById(R.id.list_title)
        texto.text = "Set List"
        val list: RecyclerView = view.findViewById(R.id.recycler_list)
        list.layoutManager = LinearLayoutManager(context)

        return view
    }
}