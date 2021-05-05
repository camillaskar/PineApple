package com.example.pineapple

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment



class BookATableDialogFragment:DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.messageDialog)
                    .setPositiveButton(R.string.book,
                            DialogInterface.OnClickListener { dialog, id ->
                                Toast.makeText(it, getString(R.string.toast, arguments?.getString(NAME) ), Toast.LENGTH_SHORT).show()
                            })
                    .setNegativeButton(R.string.cancel,
                            DialogInterface.OnClickListener { dialog, id ->
                                dialog.cancel()
                            })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object{

        const val NAME = "param"

        fun openDialog(restaurantName:String): BookATableDialogFragment {
            return BookATableDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME, restaurantName)
                }
            }

        }
    }
}

