package com.example.pineapple

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_layout.view.*


class BookATableDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            var customDialogView = layoutInflater.inflate(R.layout.dialog_layout, null)
            customDialogView.numberPick.maxValue = 5
            customDialogView.numberPick.minValue = 1
            customDialogView.datePick.minDate = System.currentTimeMillis()
//            customDialogView.datePick.maxDate = System.currentTimeMillis() + (1000*60*60*24*30)
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.messageDialog)
                .setView(customDialogView)
                .setPositiveButton(R.string.book,
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(
                            it,
                            getString(R.string.toast, arguments?.getString(NAME)),
                            Toast.LENGTH_SHORT
                        ).show()
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {

        const val NAME = "param"

        fun openDialog(restaurantName: String): BookATableDialogFragment {
            return BookATableDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME, restaurantName)
                }
            }

        }
    }
}

