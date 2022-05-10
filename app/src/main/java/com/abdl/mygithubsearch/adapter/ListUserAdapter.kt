package com.abdl.mygithubsearch.adapter

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdl.mygithubsearch.R
import com.abdl.mygithubsearch.data.remote.model.ItemsItem
import com.abdl.mygithubsearch.databinding.ActivityMainBinding
import com.abdl.mygithubsearch.databinding.ItemRowUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListUserAdapter(): RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private val mData = ArrayList<ItemsItem?>()

    fun setData(items: ArrayList<ItemsItem>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): ItemsItem? {
        return mData[position]
    }

    fun addLoadingView(){
        Handler().post {
            mData.add(null)
            notifyItemInserted(mData.size - 1)
        }
    }

    fun removeLoadingView(){
        if (mData.size != 0){
            mData.removeAt(mData.size - 1)
            notifyItemRemoved(mData.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowUserBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val mData = mData[position]
        if (mData != null) {
            holder.bind(mData)
        }
    }

    override fun getItemCount(): Int = mData.size

    inner class ListViewHolder(val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemsItem){
            with(binding){
                tvUsername.text = item.login
                tvIdUser.text = item.id.toString()

                Glide.with(itemView.context)
                    .load(item.avatarUrl)
                    .apply(RequestOptions().override(55,55))
                    .into(imgItemPhoto)
            }
        }
    }
}