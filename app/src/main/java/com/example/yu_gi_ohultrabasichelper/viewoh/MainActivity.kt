package com.example.yu_gi_ohultrabasichelper.viewoh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.yu_gi_ohultrabasichelper.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment(YugiSetsFragment(), null)
    }

    fun changeFragment(fragment: Fragment, tag:String?){
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frame, fragment, tag).commit()
    }
}