package com.example.android.roomwordssample.api

import com.example.android.roomwordssample.models.ToDo
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("todos/")
    fun getAllTodo(): Call<ToDo>

    @get:GET("users")
    val apiRequestsArray: Call<JsonArray>
}