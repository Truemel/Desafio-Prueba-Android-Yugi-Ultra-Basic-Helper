package com.example.yu_gi_ohultrabasichelper.viewoh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.yu_gi_ohultrabasichelper.R
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {

    private lateinit var mFireBaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFireBaseAnalytics = FirebaseAnalytics.getInstance(this)
        changeFragmentNoBackStack(StartFragment())
    }

    //Reemplaza por el fragmento que recibe y le entrega un tag
    fun changeFragment(fragment: Fragment, tag:String?){
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frame, fragment, tag).commit()
    }

    //Reemplaza por el fragmento que recibe sin agregarlo a un backstack
    fun changeFragmentNoBackStack(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).commit()
    }
}