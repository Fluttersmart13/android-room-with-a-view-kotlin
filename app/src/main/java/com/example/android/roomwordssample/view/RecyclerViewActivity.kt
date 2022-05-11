package com.example.android.roomwordssample.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.roomwordssample.R
import com.example.android.roomwordssample.adapter.Developer_CustomAdapter
import com.example.android.roomwordssample.databinding.ActivityRecyclerViewBinding
import com.example.android.roomwordssample.models.DeveloperModel
import com.example.android.roomwordssample.viewmodels.DeveloperViewModel
import java.util.ArrayList

class RecyclerViewActivity: AppCompatActivity() {

   internal var activityMainBinding: ActivityRecyclerViewBinding?= null

    internal var loadBar : ProgressBar? = null
    var mainViewModel: DeveloperViewModel? = null
    private var mDeveloper_CustomAdapter: Developer_CustomAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_recycler_view);

        // bind RecyclerView
        val recyclerView = activityMainBinding?.viewdeveloper
        loadBar = activityMainBinding!!.loadBar
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        recyclerView!!.setHasFixedSize(true)

        ///init the View Model
        mainViewModel = ViewModelProviders.of(this).get(DeveloperViewModel::class.java)

        //init the Custom adataper
        mDeveloper_CustomAdapter = Developer_CustomAdapter()
        //set the CustomAdapter
        recyclerView.setAdapter(mDeveloper_CustomAdapter)

        getAllDev()
    }

    private fun getAllDev() {
        ///get the list of dev from api response
        mainViewModel!!.allDeveloper.observe(this,
            Observer<List<Any>> { mDeveloperModel ->
                ///if any thing chnage the update the UI
                mDeveloper_CustomAdapter?.setDeveloperList(mDeveloperModel as ArrayList<DeveloperModel>)
                loadBar?.visibility = View.GONE
            })
    }
}
