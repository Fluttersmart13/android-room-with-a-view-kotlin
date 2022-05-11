package com.example.android.roomwordssample.interfaces

interface CallBack {
    fun onSuccess()
    fun onFail()

    fun onApiFail(message: Throwable)

    fun onApiUnsuccessResponse(responseCode: Int)
}