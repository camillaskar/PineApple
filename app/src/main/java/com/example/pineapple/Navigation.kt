package com.example.pineapple

import android.view.View
import androidx.fragment.app.Fragment

interface Navigation {
    fun openFragment(fragment: Fragment, view: View? = null)
}