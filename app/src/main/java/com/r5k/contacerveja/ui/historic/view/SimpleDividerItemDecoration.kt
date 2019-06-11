package com.r5k.contacerveja.ui.historic.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.r5k.contacerveja.R


class SimpleDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private var mDivider: Drawable? = null
    private var paddinInPx: Int = 0


    init {
        mDivider = ContextCompat.getDrawable(context,R.drawable.line_divider)
        paddinInPx = context.resources.getDimensionPixelOffset(R.dimen.divider_margin)

    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft + paddinInPx
        val right = parent.width - parent.paddingRight -paddinInPx

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight

            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(c)
        }
    }
}