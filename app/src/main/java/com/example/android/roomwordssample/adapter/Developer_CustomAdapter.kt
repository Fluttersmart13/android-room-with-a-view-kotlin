package com.example.android.roomwordssample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.android.roomwordssample.R
import com.example.android.roomwordssample.databinding.RowListItemBinding
import com.example.android.roomwordssample.models.DeveloperModel

import java.util.ArrayList

class Developer_CustomAdapter : RecyclerView.Adapter<Developer_CustomAdapter.DeveloperViewHolder>() {

    private var mDeveloperModel: ArrayList<DeveloperModel>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DeveloperViewHolder {
        val mDeveloperListItemBinding = DataBindingUtil.inflate<RowListItemBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.row_list_item, viewGroup, false
        )

        return DeveloperViewHolder(mDeveloperListItemBinding)
    }

    override fun onBindViewHolder(mDeveloperViewHolder: DeveloperViewHolder, i: Int) {
        val currentStudent = mDeveloperModel!![i]

        
        mDeveloperViewHolder.mDeveloperListItemBinding.developerModel = currentStudent


    }

    override fun getItemCount(): Int {
        return if (mDeveloperModel != null) {
            mDeveloperModel!!.size
        } else {
            0
        }
    }

    fun setDeveloperList(mDeveloperModel: ArrayList<DeveloperModel>) {
        this.mDeveloperModel = mDeveloperModel
        notifyDataSetChanged()
    }

    inner class DeveloperViewHolder(var mDeveloperListItemBinding: RowListItemBinding) :
        RecyclerView.ViewHolder(mDeveloperListItemBinding.root)
}
