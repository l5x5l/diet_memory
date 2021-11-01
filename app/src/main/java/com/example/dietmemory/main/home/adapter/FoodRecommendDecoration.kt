package com.example.dietmemory.main.home.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dietmemory.utils.dpToPx

class FoodRecommendDecoration(context : Context) : RecyclerView.ItemDecoration() {
    private val size6 = dpToPx(context, 6)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildLayoutPosition(view)

        if (position == 0){
            outRect.left = 0
        } else {
            outRect.left = size6
        }
    }
}