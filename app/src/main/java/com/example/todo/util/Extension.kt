package com.example.todo.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation

fun Navigation.sent(v: View, id: Int) {
    findNavController(v).navigate(id)
}

fun Context.showToast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
