package com.andela.cityweatherforecast.bookmarks

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.andela.cityweatherforecast.R
import java.lang.ClassCastException
import java.lang.IllegalStateException

class ConfirmBookmarkDialog : DialogFragment() {

    private lateinit var listener: ConfirmBookmarkDialogListener

    interface ConfirmBookmarkDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            listener = targetFragment as ConfirmBookmarkDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("${targetFragment.toString()} must implement ConfirmBookmarkDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val layout = inflater.inflate(R.layout.dialog_confirm_bookmark, null)
            layout.findViewById<TextView>(R.id.cityNameAndLocation).text = arguments?.getString("city_string")
            builder.setView(layout)
                .setPositiveButton(R.string.confirm_string) { _, _ ->
                    listener.onDialogPositiveClick(this)
                }
                .setNegativeButton(R.string.cancel_string) { _, _ ->
                    listener.onDialogNegativeClick(this)
                }
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}