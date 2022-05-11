package com.example.android.roomwordssample.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.android.roomwordssample.models.DeveloperModel


import com.example.android.roomwordssample.repository.DeveloperRepository


class DeveloperViewModel(application: Application) : AndroidViewModel(application) {
    private val mDeveloperRepository: DeveloperRepository

    val allDeveloper: LiveData<List<DeveloperModel>>
        get() = mDeveloperRepository.getMutableLiveData()

    init {
        mDeveloperRepository = DeveloperRepository()
    }
}
