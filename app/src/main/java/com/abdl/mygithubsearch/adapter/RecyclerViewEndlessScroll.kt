package com.abdl.mygithubsearch.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewEndlessScroll : RecyclerView.OnScrollListener {
    private var visibleThresHold = 5
    private lateinit var mOnLoadMoreListener: OnLoadMoreListener
    private var isLoading: Boolean = false
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    fun setLoaded(){
        isLoading = false
    }

    fun getLoaded(): Boolean{
        return isLoading
    }

    fun setOnLoadMoreListener(mOnLoadMoreListener: OnLoadMoreListener){
        this.mOnLoadMoreListener = mOnLoadMoreListener
    }

    constructor(layoutManager: LinearLayoutManager){
        this.mLayoutManager = layoutManager
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy <= 0) return

        totalItemCount = mLayoutManager.itemCount

        lastVisibleItem = (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()

        if (!isLoading && totalItemCount <= lastVisibleItem + visibleThresHold){
            mOnLoadMoreListener.onLoadMore()
            isLoading = true
        }

    }

    private fun getLastVisibleItem(lastVisibleItemPosition: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPosition.indices){
            if (i == 0){
                maxSize = lastVisibleItemPosition[i]
            } else if (lastVisibleItemPosition[i] > maxSize){
                maxSize = lastVisibleItemPosition[i]
            }
        }
        return maxSize
    }

    interface OnLoadMoreListener{
        fun onLoadMore()
    }
}

