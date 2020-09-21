package com.example.yu_gi_ohultrabasichelper.viewoh

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yu_gi_ohultrabasichelper.R
import com.example.yu_gi_ohultrabasichelper.viewoh.yugi_helper.YugiHelperFragment
import com.example.yu_gi_ohultrabasichelper.viewoh.yugi_helper.YugiHelperLandscapeActivity
import com.google.android.material.button.MaterialButton

class StartFragment:Fragment(), View.OnClickListener {

    private lateinit var cList:MaterialButton
    private lateinit var fList:MaterialButton
    private lateinit var yHelper:MaterialButton

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

        if(v!!.id == yHelper.id){
            val inten:Intent = Intent(context as MainActivity, YugiHelperLandscapeActivity::class.java)
            startActivity(inten)
        }else{
            if(v.id == cList.id)
                frag = YugiSetsFragment()
            else if(v.id == fList.id)
                frag = YugiFavListFragment()
            else
                frag = YugiHelperFragment()

            (context as MainActivity).changeFragment(frag, null)
        }
    }
}