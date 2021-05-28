package com.example.pineapple

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), Navigation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var feed = FeedFragment()
        openFragment(FeedFragment())
        getString(R.string.reviewRes)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun openFragment(fragment: Fragment, view: View?) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setReorderingAllowed(true)

        if (view != null) {
            fragmentTransaction.addSharedElement(view, view.transitionName)
        }
        fragmentTransaction.replace(R.id.container, fragment)
            .addToBackStack(fragment::class.java.name)
            .commit()
    }
}