package com.example.yu_gi_ohultrabasichelper.viewoh.yugi_helper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import com.example.yu_gi_ohultrabasichelper.R
import com.google.android.material.button.MaterialButton

class YugiCardZoneModDialog(var onButtonClick:OnDialogButtonClickListener, var isMonster:Boolean):DialogFragment(), View.OnClickListener {

    private lateinit var atkZ:ViewGroup
    private lateinit var defZ:ViewGroup

    private lateinit var counter:EditText
    private lateinit var spellC:EditText
    private lateinit var predC:EditText
    private lateinit var dragC:EditText
    private lateinit var atk:EditText
    private lateinit var def:EditText
    private lateinit var counterPl:RadioButton
    private lateinit var counterMi:RadioButton
    private lateinit var counterRe:RadioButton
    private lateinit var spellCPl:RadioButton
    private lateinit var spellCMi:RadioButton
    private lateinit var spellCRe:RadioButton
    private lateinit var predCPl:RadioButton
    private lateinit var predCMi:RadioButton
    private lateinit var predCRe:RadioButton
    private lateinit var dragCPl:RadioButton
    private lateinit var dragCMi:RadioButton
    private lateinit var dragCRe:RadioButton
    private lateinit var atkPl:RadioButton
    private lateinit var atkMi:RadioButton
    private lateinit var atkRe:RadioButton
    private lateinit var defPl:RadioButton
    private lateinit var defMi:RadioButton
    private lateinit var defRe:RadioButton
    private lateinit var submit:MaterialButton
    private lateinit var reset:MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.ultra_card_mod_dialog_layout, container, false)
        atkZ = view.findViewById(R.id.atk_zone)
        defZ = view.findViewById(R.id.def_zone)
        if(!isMonster){
            atkZ.visibility = View.GONE
            defZ.visibility = View.GONE
        }

        counter = view.findViewById(R.id.counter_total)
        spellC = view.findViewById(R.id.spellC_total)
        predC = view.findViewById(R.id.predC_total)
        dragC = view.findViewById(R.id.dragC_total)
        atk = view.findViewById(R.id.atk_mod)
        def = view.findViewById(R.id.def_mod)
        counterPl = view.findViewById(R.id.plus_counter)
        counterMi = view.findViewById(R.id.minus_counter)
        counterRe = view.findViewById(R.id.reset_counter)
        spellCPl = view.findViewById(R.id.plus_spellC)
        spellCMi = view.findViewById(R.id.minus_spellC)
        spellCRe = view.findViewById(R.id.reset_spellC)
        predCPl = view.findViewById(R.id.plus_predC)
        predCMi = view.findViewById(R.id.minus_predC)
        predCRe = view.findViewById(R.id.reset_predC)
        dragCPl = view.findViewById(R.id.plus_dragC)
        dragCMi = view.findViewById(R.id.minus_dragC)
        dragCRe = view.findViewById(R.id.reset_dragC)
        atkPl = view.findViewById(R.id.plus_atk)
        atkMi = view.findViewById(R.id.minus_atk)
        atkRe = view.findViewById(R.id.reset_atk)
        defPl = view.findViewById(R.id.plus_def)
        defMi = view.findViewById(R.id.minus_def)
        defRe = view.findViewById(R.id.reset_def)
        submit = view.findViewById(R.id.submit)
        reset = view.findViewById(R.id.reset)
        submit.setOnClickListener(this)
        reset.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        if(v!!.id == submit.id){
            var coun = counter.text.toString().toInt()
            if(counterMi.isChecked)
                coun *= -1
            var spell = spellC.text.toString().toInt()
            if(spellCMi.isChecked)
                spell *= -1
            var pred = predC.text.toString().toInt()
            if(predCMi.isChecked)
                pred *= -1
            var drag = dragC.text.toString().toInt()
            if(dragCMi.isChecked)
                drag *= -1
            var at = atk.text.toString().toInt()
            if(atkMi.isChecked)
                at *= -1
            var de = def.text.toString().toInt()
            if(defMi.isChecked)
                de *= -1
            onButtonClick.onSubmit(coun, spell, pred, drag, at, de)
            onButtonClick.onReset(counterRe.isChecked, spellCRe.isChecked, predCRe.isChecked, dragCRe.isChecked, atkRe.isChecked, defRe.isChecked)
        }else if(v.id == reset.id)
            onButtonClick.onReset(true, true, true, true, true, true)
        dismiss()
    }

    interface OnDialogButtonClickListener{
        fun onSubmit(counterV:Int, spellCV:Int, predCV:Int, dragCV:Int, atkV:Int, defV:Int)
        fun onReset(counterR:Boolean, spellCR:Boolean, predCR:Boolean, dragCR:Boolean, atkR:Boolean, defR:Boolean)
    }
}