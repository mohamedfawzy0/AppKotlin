package com.appkotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.appkotlin.R
import com.appkotlin.databinding.ListItemBinding
import com.appkotlin.models.entity.User
import java.util.ArrayList

class UserAdapter( var userList: List<User>):RecyclerView.Adapter<UserAdapter.MyHolder>(){

    lateinit var inflater: LayoutInflater
    lateinit var context:Context
    var onListItemClick:OnListItemClick?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyHolder {
        context=parent.context
        inflater=LayoutInflater.from(parent.context)
        var binding:ListItemBinding=DataBindingUtil.inflate(inflater,R.layout.list_item,parent,false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding.user=userList[position]

        holder.itemView.setOnClickListener {
            onListItemClick?.onItemClick(user = userList.get(position))
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateList(userList: List<User>){
        this.userList=userList
        notifyDataSetChanged()
    }

    inner class MyHolder(binding:ListItemBinding) :RecyclerView.ViewHolder(binding.root) {
         var binding:ListItemBinding=binding

    }
    interface OnListItemClick{
        fun onItemClick(user: User)
    }
}


