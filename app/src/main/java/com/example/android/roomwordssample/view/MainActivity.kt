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
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment.getExternalStorageDirectory
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.roomwordssample.*
import com.example.android.roomwordssample.adapter.WordListAdapter
import com.example.android.roomwordssample.models.Word
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File

class MainActivity : AppCompatActivity() {


    private val newWordActivityRequestCode = 1
    lateinit var context: Context
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

        
        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        wordViewModel.allWords.observe(owner = this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
        }

        getMethod()

        try{
            val appDirctory =File(getExternalStorageDirectory().absolutePath + "/cdp_image")
            appDirctory.mkdirs()
        }catch ( e:Exception){
            Log.d("iamge",e.message.toString())
        }
        callApi();

    }

    private fun callApi() {
//        RetrofitClient(context).instance.getAllTodo().enqueue(object :
//            Callback<ToDo> {
//            override fun onResponse(
//                call: Call<ToDo>,
//                response: Response<ToDo>
//            ) {
//                if (response.isSuccessful) {
//                   println("response_success")
//                } else {
//                    println("response_fail")
//                }
//            }
//            override fun onFailure(call: Call<ToDo>, t: Throwable) {
//                println("response_fail")
//            }
//        })
    }

    private fun getMethod() {
        var str = "Hello World"
        str.let { println("$it!!") }
        println(str)

        var a = 1
        var b= 2

        a = a.let { it + 2 }.let { val i = it + b
            i}

        println(a)


        //var x = "Anupam"
        //x.let { outer -> outer.let { inner -> print("Inner is $inner and outer is $outer") } }

        var x = "Anupam"
        x = x.let { outer ->
            outer.let { inner ->
                println("Inner is $inner and outer is $outer")
                "Kotlin Tutorials Inner let"
            }
            "Kotlin Tutorials Outer let"
        }
        println(x)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { reply ->
                val word = Word(reply)
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
