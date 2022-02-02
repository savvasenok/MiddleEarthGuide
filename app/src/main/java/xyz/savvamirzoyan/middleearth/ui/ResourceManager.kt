package xyz.savvamirzoyan.middleearth.ui

import android.content.Context
import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes stringId: Int): String

    class Base(private val context: Context) : ResourceManager {

        override fun getString(stringId: Int): String = context.getString(stringId)
    }
}