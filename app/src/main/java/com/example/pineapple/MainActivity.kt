package com.example.pineapple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.restaurant_row.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var feed = Feed()
        addFragment(Feed())

    }
    fun addFragment(fragment: Fragment){
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment::class.java.name)
                .commit()
    }
}