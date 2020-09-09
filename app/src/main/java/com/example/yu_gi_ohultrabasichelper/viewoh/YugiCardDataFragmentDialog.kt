package com.example.yu_gi_ohultrabasichelper.viewoh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.yu_gi_ohultrabasichelper.R
import com.example.yu_gi_ohultrabasichelper.extras.IMAGES_CARDS
import com.example.yu_gi_ohultrabasichelper.extras.JPG
import com.example.yu_gi_ohultrabasichelper.extras.fixNameToPath
import com.example.yu_gi_ohultrabasichelper.modeloh.retrofit.CardDataRetro
import com.example.yu_gi_ohultrabasichelper.viewmodeloh.YugiViewModel
import com.squareup.picasso.Picasso

class YugiCardDataFragmentDialog:DialogFragment(), View.OnClickListener {

    lateinit var cardName: TextView
    lateinit var cardType: TextView
    lateinit var property: TextView
    lateinit var type: TextView
    lateinit var family: TextView
    lateinit var image:ImageView
    lateinit var level:EditText
    lateinit var atk:EditText
    lateinit var def:EditText
    lateinit var cardText: EditText
    lateinit var save:Button
    lateinit var vModel:YugiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vModel = ViewModelProvider(this).get(YugiViewModel::class.java)
        if(vModel.setCardDataToViewModel(tag!!))
            vModel.getCardDataFromRetro(tag!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.card_data_layout, container, false)
        cardName = view.findViewById(R.id.card_name)
        cardType = view.findViewById(R.id.card_type)
        property = view.findViewById(R.id.card_property)
        type = view.findViewById(R.id.type)
        family = view.findViewById(R.id.family)
        image = view.findViewById(R.id.card_image)
        level = view.findViewById(R.id.card_level)
        atk = view.findViewById(R.id.card_atk)
        def = view.findViewById(R.id.card_def)
        cardText = view.findViewById(R.id.card_text)
        save = view.findViewById(R.id.save_button)
        save.setOnClickListener(this)

        vModel.yugiCardData.observe(context as MainActivity, Observer { cardName.text = it.name
        cardType.text = it.card_type
        property.text = it.property
        type.text = it.type
        family.text = it.family
        Picasso.get().load(IMAGES_CARDS+ fixNameToPath(it.name)+ JPG).into(image)
        level.setText(it.level.toString())
        atk.setText(it.atk.toString())
        def.setText(it.def.toString())
        cardText.setText(it.text) })

        return view
    }

    override fun onClick(v: View?) {
        vModel.insertFavCard(CardDataRetro(cardName.text.toString(), cardText.text.toString(), cardType.text.toString(), type.text.toString(), family.text.toString(), atk.text.toString().toInt(), def.text.toString().toInt(), level.text.toString().toShort(), property.text.toString()))
    }
}