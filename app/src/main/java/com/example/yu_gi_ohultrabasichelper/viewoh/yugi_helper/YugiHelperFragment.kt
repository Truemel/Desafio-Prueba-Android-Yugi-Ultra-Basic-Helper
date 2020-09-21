package com.example.yu_gi_ohultrabasichelper.viewoh.yugi_helper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.yu_gi_ohultrabasichelper.R
import com.google.android.material.button.MaterialButton

class YugiHelperFragment: Fragment(), View.OnClickListener {

    private lateinit var exMon1:FrameLayout
    private lateinit var exMon2:FrameLayout
    private lateinit var pen1:FrameLayout
    private lateinit var pen2:FrameLayout
    private var master3:Boolean = false
    private var listenerList:MutableList<OnResetAllListener> = mutableListOf()

    private lateinit var ruleButton:MaterialButton
    private lateinit var resetButton:MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.ultra_field_layout, container, false)
        exMon1 = view.findViewById(R.id.ex1)
        exMon2 = view.findViewById(R.id.ex2)
        pen1 = view.findViewById(R.id.pend1)
        pen2 = view.findViewById(R.id.pend2)

        ruleButton = view.findViewById(R.id.change_format)
        resetButton = view.findViewById(R.id.reset_button)
        ruleButton.setOnClickListener(this)
        resetButton.setOnClickListener(this)

        pen1.visibility = View.GONE
        pen2.visibility = View.GONE

        ini()

        return view
    }

    private fun ini(){
        var cardZone = YugiCardZoneFragment(false)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.field_c)
        cardZone  = YugiCardZoneFragment(true)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.ex1)
        cardZone = YugiCardZoneFragment(true)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.ex2)
        cardZone  = YugiCardZoneFragment(false)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.pend1)
        cardZone  = YugiCardZoneFragment(true)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.m1)
        cardZone = YugiCardZoneFragment(true)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.m2)
        cardZone  = YugiCardZoneFragment(true)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.m3)
        cardZone = YugiCardZoneFragment(true)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.m4)
        cardZone = YugiCardZoneFragment(true)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.m5)
        cardZone  = YugiCardZoneFragment(false)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.pend2)
        cardZone = YugiCardZoneFragment(false)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.mag1)
        cardZone = YugiCardZoneFragment(false)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.mag2)
        cardZone = YugiCardZoneFragment(false)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.mag3)
        cardZone = YugiCardZoneFragment(false)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.mag4)
        cardZone = YugiCardZoneFragment(false)
        listenerList.add(cardZone)
        setFragment(cardZone, R.id.mag5)
    }

    //Funcion para colocar un fragmento en un viewgroupID
    fun setFragment(fragment: Fragment, res:Int){
        (context as YugiHelperLandscapeActivity).supportFragmentManager.beginTransaction().replace(res, fragment).commit()
    }

    override fun onClick(v: View?) {
        if(v!!.id == ruleButton.id){
            master3 = !master3
            if(master3){
                pen1.visibility = View.VISIBLE
                pen2.visibility = View.VISIBLE
                exMon1.visibility = View.INVISIBLE
                exMon2.visibility = View.INVISIBLE
                ruleButton.text = getString(R.string.mast5)
            }else{
                pen1.visibility = View.GONE
                pen2.visibility = View.GONE
                exMon1.visibility = View.VISIBLE
                exMon2.visibility = View.VISIBLE
                ruleButton.text = getString(R.string.mast3)
            }
        }else if(v.id == resetButton.id)
            for(aux in listenerList)
                aux.onResetAll()
    }

    interface OnResetAllListener{
        fun onResetAll()
    }

}