package com.example.pineapple

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_account.view.*

class AcccountFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var account = inflater.inflate(R.layout.fragment_account, container, false)

        var forgot = account.login_forgot
        var signup = account.signup
        return super.onCreateView(inflater, container, savedInstanceState)
    }




    /**
     * A function to Log-In. The user will be able to log in using the registered email and password with Firebase Authentication.
     */
    private fun logInRegisteredUser() {


    }
}

