/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.roomwordssample.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.android.roomwordssample.R

/**
 * Activity for entering a word.
 */

class NewWordActivity : BaseActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        val editWordView = findViewById<EditText>(R.id.edit_word)

        val button = findViewById<Button>(R.id.button_save)
        val recyclerview = findViewById<Button>(R.id.recyclerview)
        val alertBox = findViewById<Button>(R.id.alertBox)
        val deleteAll = findViewById<Button>(R.id.deleteAll)

        recyclerview.setOnClickListener {
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }

        deleteAll.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_SAVE, "delete")
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }

        alertBox.setOnClickListener {
            showShortToast("Alert")
            showAlertOk("title","description","ok")
        }
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editWordView.text.toString()
                replyIntent.putExtra(EXTRA_SAVE, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_SAVE = "save"
    }
}
