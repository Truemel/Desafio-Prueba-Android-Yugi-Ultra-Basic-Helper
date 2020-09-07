package com.example.yu_gi_ohultrabasichelper.viewoh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yu_gi_ohultrabasichelper.R
import com.example.yu_gi_ohultrabasichelper.modeloh.retrofit.YugiRetrofitRequests
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YugiSetsFragment:Fragment(), Callback<MutableList<String>> {

    lateinit var list:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.recycler_list_layout, container, false)
        val texto:TextView = view.findViewById(R.id.list_title)
        texto.text = "Set List"
        list = view.findViewById(R.id.recycler_list)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = YugiSetsAdapter(mutableListOf(), context as MainActivity)

        YugiRetrofitRequests().getYugiSetsList(this)

        return view
    }

    override fun onResponse(
        call: Call<MutableList<String>>,
        response: Response<MutableList<String>>
    ) {
        if(response.isSuccessful && response.body()!!.size > 0)
            (list.adapter as YugiSetsAdapter).updateList(response.body()!!)
    }

    override fun onFailure(call: Call<MutableList<String>>, t: Throwable) {
        Toast.makeText(context, "Error, couldn't get set list", Toast.LENGTH_LONG).show()
    }
}