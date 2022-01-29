package xyz.savvamirzoyan.middleearth.ui.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class CoreRecyclerViewAdapter<T, VH : CoreViewHolder<T>> : RecyclerView.Adapter<VH>() {

    protected val items = ArrayList<T>()

    open fun update(data: List<T>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    protected fun Int.makeView(parent: ViewGroup): View =
        LayoutInflater.from(parent.context).inflate(this, parent, false)
}