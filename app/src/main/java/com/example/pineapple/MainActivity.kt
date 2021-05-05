package com.example.pineapple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.util.Base64
import kotlinx.android.synthetic.main.restaurant_row.*

class MainActivity : AppCompatActivity(), Navigation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var feed = Feed()
        openFragment(Feed())

        var response = ResponseCall()
        var test1 = response.getResponse()
        var test = response.getResponse()

        var jsonResponse = SingletonResponseCall.getResponse()
        var jsonResponse2 = SingletonResponseCall.getResponse()

        getString(R.string.reviewRes)
        resources.getColor(R.color.blackk)
        resources.getDimension(R.dimen.margin_sec_small)
        resources.getString(R.string.bottom_sheet_behavior)

    }

    override fun openFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment::class.java.name)
                .commit()
    }




//    fun openFragment(fragment: Fragment){
//        supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.container, fragment)
//                .addToBackStack(fragment::class.java.name)
//                .commit()
//    }
}