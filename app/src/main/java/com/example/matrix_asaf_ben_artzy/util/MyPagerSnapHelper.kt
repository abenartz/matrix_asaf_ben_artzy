package com.example.matrix_asaf_ben_artzy.util

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

class MyPagerSnapHelper: PagerSnapHelper() {

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        if (layoutManager is LinearLayoutManager) {
            if (shouldNotSnap(layoutManager)) {
                return null
            }
        }
        return super.findSnapView(layoutManager)
    }

    private fun shouldNotSnap(linearLayoutManager: LinearLayoutManager): Boolean {
        val isFirstItemVisible = linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0
        val isLastItemVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition() == linearLayoutManager.itemCount - 1
        return  isFirstItemVisible || isLastItemVisible
    }
}