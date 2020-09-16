package com.example.yu_gi_ohultrabasichelper.viewoh.yugi_helper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.yu_gi_ohultrabasichelper.R

class YugiHelperLandscapeActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(supportActionBar != null)
            supportActionBar!!.hide()
        setContentView(R.layout.activity_main)
        changeFragment(YugiHelperFragment())
    }

    //Reemplaza por el fragmento que recibe y le entrega un tag
    fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).commit()
    }
}