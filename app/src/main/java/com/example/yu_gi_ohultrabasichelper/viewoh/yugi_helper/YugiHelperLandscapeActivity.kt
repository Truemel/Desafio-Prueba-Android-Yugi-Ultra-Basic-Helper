package com.example.yu_gi_ohultrabasichelper.viewoh.yugi_helper

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.yu_gi_ohultrabasichelper.R
import com.google.firebase.analytics.FirebaseAnalytics

class YugiHelperLandscapeActivity:AppCompatActivity() {

    private lateinit var mFireBaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        if(supportActionBar != null)
            supportActionBar!!.hide()
        setContentView(R.layout.activity_main)
        mFireBaseAnalytics = FirebaseAnalytics.getInstance(this)
        changeFragment(YugiHelperFragment())
    }

    //Reemplaza por el fragmento que recibe y le entrega un tag
    fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).commit()
    }
}