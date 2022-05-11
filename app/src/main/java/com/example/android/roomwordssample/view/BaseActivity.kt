package com.example.android.roomwordssample.view

import android.R
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity() {
    lateinit var ui_handler : Handler
    companion object
    {
        lateinit var context: Context
        lateinit var activity: Activity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        activity = this
        ui_handler = Handler(context.mainLooper)
        Log.d("StringLog","string")
    }

    open fun showShortToast(msg: String) {
        if (msg.length > 0) {
            ui_handler.post(Runnable { Toast.makeText(context, msg, Toast.LENGTH_SHORT).show() })
        }
    }

    open fun showAlertOk(title: String?, description: String?, ok_btn_title: String?, ) {
        val alertDialog = AlertDialog.Builder(context)
            .setIcon(R.drawable.ic_dialog_alert)
            .setTitle(title)
            .setMessage(description)
            .setPositiveButton(ok_btn_title) { dialogInterface, i ->
                dialogInterface.dismiss()
            }
        alertDialog.setCancelable(false)
        alertDialog.create().show()
    }
}