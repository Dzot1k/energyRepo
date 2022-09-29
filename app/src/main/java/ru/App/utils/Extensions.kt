package ru.App.utils

import android.graphics.Color
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment

fun AppCompatTextView.green(normal: String) {
    setTextColor(Color.GREEN)
    text = normal
}

fun AppCompatTextView.red(normal: String) {
    setTextColor(Color.RED)
    text = normal
}

fun Fragment.showToast(text: String){
    val toast = Toast.makeText(
        context,
        text,
        Toast.LENGTH_SHORT
    )
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}