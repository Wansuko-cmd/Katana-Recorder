package com.wsr.katanarecorder.ui.list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_contents.view.*

class ListViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val title: TextView = view.title
}