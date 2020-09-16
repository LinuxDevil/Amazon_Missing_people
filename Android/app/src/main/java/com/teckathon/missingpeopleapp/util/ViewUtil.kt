package com.teckathon.missingpeopleapp.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * a function to quickly display a toast message
 * @param message
 * @see Context.toast
 * @sample toast("Message")
 */
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

/**
 * a function to show the progress bar
 * @see ProgressBar
 */
fun ProgressBar.show() {
    visibility = View.VISIBLE
}

/**
 * a function to hide the progress bar
 * @see ProgressBar
 */
fun ProgressBar.hide() {
    visibility = View.GONE
}

/**
 *
 * @receiver View
 * @param message String
 */
fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}