package com.example.matrix_asaf_ben_artzy.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class TopSpacingItemDecoration(
    private val itemMargin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildLayoutPosition(view)

        outRect.left = itemMargin
        outRect.right =  if (position == 0) 0 else itemMargin
        outRect.bottom = itemMargin
        outRect.top = itemMargin

    }
}