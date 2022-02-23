package com.myfitness.adapters

import androidx.recyclerview.widget.DiffUtil
import com.myfitness.models.Result

class UserListDiffUtil(
    private val oldList : List<Result>,
    private val newList : List<Result>

)  : DiffUtil.Callback(){

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].name == newList[newItemPosition].name
                && oldList[oldItemPosition].picture == newList[newItemPosition].picture
                && oldList[oldItemPosition].registered == newList[newItemPosition].registered
                && oldList[oldItemPosition].location == newList[newItemPosition].location

    }


}