package com.example.yu_gi_ohultrabasichelper.viewoh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.yu_gi_ohultrabasichelper.R
import com.example.yu_gi_ohultrabasichelper.extras.getCardImagePath
import com.example.yu_gi_ohultrabasichelper.modeloh.room.YugiFavouriteTablePojo
import com.example.yu_gi_ohultrabasichelper.viewmodeloh.YugiViewModel
import com.squareup.picasso.Picasso

class YugiFavDataDialogFragment:DialogFragment(), View.OnClickListener {

    private lateinit var cardName: TextView
    private lateinit var cardType: TextView
    private lateinit var property: TextView
    private lateinit var type: TextView
    private lateinit var family: TextView
    private lateinit var image: ImageView
    private lateinit var level: EditText
    private lateinit var atk: EditText
    private lateinit var def: EditText
    private lateinit var cardText: EditText
    private lateinit var save: Button
    private lateinit var vModel: YugiViewModel
    private var favCard:YugiFavouriteTablePojo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vModel = ViewModelProvider(this).get(YugiViewModel::class.java)
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
        save.text = "Save Card Changes"
        save.setOnClickListener(this)

        vModel.yugiFavList.observe(context as MainActivity, Observer {
            favCard = it[tag!!.toInt()]
            cardName.text = favCard!!.name
            cardType.text = favCard!!.card_type
            property.text = favCard!!.property
            type.text = favCard!!.type
            family.text = favCard!!.family
            Picasso.get().load(getCardImagePath(favCard!!.name)).into(image)
            level.setText(favCard!!.level.toString())
            atk.setText(favCard!!.atk.toString())
            def.setText(favCard!!.def.toString())
            cardText.setText(favCard!!.text)
        })

        return view
    }

    override fun onClick(v: View?) {
        favCard = YugiFavouriteTablePojo(cardName.text.toString(), cardText.text.toString(), cardType.text.toString(), type.text.toString(), family.text.toString(), atk.text.toString().toInt(), def.text.toString().toInt(), level.text.toString().toShort(), property.text.toString())
        vModel.updateFavCard(favCard!!)
        Toast.makeText(context, "Favourite card updated", Toast.LENGTH_LONG).show()
        dismiss()
    }
}