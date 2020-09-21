package com.example.yu_gi_ohultrabasichelper.viewoh.yugi_helper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.yu_gi_ohultrabasichelper.R

class YugiCardZoneFragment(var isMonster:Boolean):Fragment(), YugiHelperFragment.OnResetAllListener,
    View.OnClickListener, YugiCardZoneModDialog.OnDialogButtonClickListener {

    private lateinit var counterZ:ViewGroup
    private lateinit var spellCZ:ViewGroup
    private lateinit var predCZ:ViewGroup
    private lateinit var dragCZ:ViewGroup
    private lateinit var atkZ:ViewGroup
    private lateinit var defZ:ViewGroup

    private lateinit var counterT:TextView
    private lateinit var spellCT:TextView
    private lateinit var predCT:TextView
    private lateinit var dragCT:TextView
    private lateinit var atkT:TextView
    private lateinit var defT:TextView

    private var counter:Int = 0
    private var spellC:Int = 0
    private var predC:Int = 0
    private var dragC:Int = 0
    private var atk:Int = 0
    private var def:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.ultra_card_mods_layout, container, false)
        counterZ = view.findViewById(R.id.counter_zone)
        counterZ.visibility = View.INVISIBLE
        spellCZ = view.findViewById(R.id.spellC_zone)
        spellCZ.visibility = View.INVISIBLE
        predCZ = view.findViewById(R.id.predC_zone)
        predCZ.visibility = View.INVISIBLE
        dragCZ = view.findViewById(R.id.dragC_zone)
        dragCZ.visibility = View.INVISIBLE
        atkZ = view.findViewById(R.id.atk_zone)
        defZ = view.findViewById(R.id.def_zone)
        if(!isMonster){
            atkZ.visibility = View.INVISIBLE
            defZ.visibility = View.INVISIBLE
        }

        counterT = view.findViewById(R.id.counter_total)
        spellCT = view.findViewById(R.id.spellC_total)
        predCT = view.findViewById(R.id.predC_total)
        dragCT = view.findViewById(R.id.dragC_total)
        atkT = view.findViewById(R.id.atk_mod)
        defT = view.findViewById(R.id.def_mod)

        view.setOnClickListener(this)

        return view
    }

    override fun onResetAll() {
        counter = 0
        spellC = 0
        predC = 0
        dragC = 0
        atk = 0
        def = 0
        counterZ.visibility = View.INVISIBLE
        spellCZ.visibility = View.INVISIBLE
        predCZ.visibility = View.INVISIBLE
        dragCZ.visibility = View.INVISIBLE
        atkT.text = "+0"
        defT.text = "+0"
    }

    override fun onClick(v: View?) {
        val dialog:YugiCardZoneModDialog = YugiCardZoneModDialog(this, isMonster)
        dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.HelperTheme)
        dialog.showNow((context as YugiHelperLandscapeActivity).supportFragmentManager, null)
    }

    override fun onSubmit(
        counterV: Int,
        spellCV: Int,
        predCV: Int,
        dragCV: Int,
        atkV: Int,
        defV: Int
    ) {
        counter += counterV
        spellC += spellCV
        predC += predCV
        dragC += dragCV

        if(counter < 0)
            counter = 0
        if(spellC < 0)
            spellC = 0
        if(predC < 0)
            predC = 0
        if(dragC < 0)
            dragC = 0

        counterT.text = counter.toString()
        spellCT.text = spellC.toString()
        predCT.text = predC.toString()
        dragCT.text = dragC.toString()

        if(counter > 0)
            counterZ.visibility = View.VISIBLE
        else
            counterZ.visibility = View.INVISIBLE

        if(spellC > 0)
            spellCZ.visibility = View.VISIBLE
        else
            spellCZ.visibility = View.INVISIBLE

        if(predC > 0)
            predCZ.visibility = View.VISIBLE
        else
            predCZ.visibility = View.INVISIBLE

        if(dragC > 0)
            dragCZ.visibility = View.VISIBLE
        else
            dragCZ.visibility = View.INVISIBLE

        if(isMonster){
            atk += atkV
            def += defV
            var atkS = ""
            var defS = ""

            if(atk >= 0)
                atkS = "+"
            else
                atkS = "-"

            if(def >= 0)
                defS = "+"
            else
                defS = "-"

            atkT.text = atkS+atk
            defT.text = defS+def
        }
    }

    override fun onReset(
        counterR: Boolean,
        spellCR: Boolean,
        predCR: Boolean,
        dragCR: Boolean,
        atkR: Boolean,
        defR: Boolean
    ) {
        if(counterR){
            counter = 0
            counterT.text = counter.toString()
            counterZ.visibility = View.INVISIBLE
        }
        if(spellCR){
            spellC = 0
            spellCT.text = spellC.toString()
            spellCZ.visibility = View.INVISIBLE
        }
        if(predCR){
            predC = 0
            predCT.text = predC.toString()
            predCZ.visibility = View.INVISIBLE
        }
        if(dragCR){
            dragC = 0
            dragCT.text = dragC.toString()
            dragCZ.visibility = View.INVISIBLE
        }
        if(atkR && isMonster){
            atk = 0
            atkT.text = atk.toString()
        }
        if(defR && isMonster){
            def = 0
            defT.text = def.toString()
        }
    }
}