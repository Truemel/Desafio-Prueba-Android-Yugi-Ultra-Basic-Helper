package com.example.yu_gi_ohultrabasichelper.viewoh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yu_gi_ohultrabasichelper.R
import com.google.android.material.button.MaterialButton

class StartFragment:Fragment(), View.OnClickListener {

    lateinit var cList:MaterialButton
    lateinit var fList:MaterialButton
    lateinit var yHelper:MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.start_layout_fragment, container, false)
        cList = view.findViewById(R.id.card_list)
        fList = view.findViewById(R.id.fav_list)
        yHelper = view.findViewById(R.id.yugi_helper)
        cList.setOnClickListener(this)
        fList.setOnClickListener(this)
        yHelper.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        lateinit var frag:Fragment

        if(v!!.id == cList.id)
            frag = YugiSetsFragment()
        else if(v!!.id == fList.id)
            frag = YugiFavListFragment()
        else
            frag = YugiSetsFragment()

        (context as MainActivity).changeFragment(frag, null)
    }
}