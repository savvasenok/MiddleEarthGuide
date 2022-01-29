package xyz.savvamirzoyan.middleearth.ui.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class CoreViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T)
}