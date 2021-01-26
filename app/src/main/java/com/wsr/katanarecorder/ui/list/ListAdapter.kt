package com.wsr.katanarecorder.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wsr.katanarecorder.R
import com.wsr.katanarecorder.db.KatanaData

class ListAdapter :RecyclerView.Adapter<ListViewHolder>(){
    private var data: MutableList<KatanaData> = mutableListOf()

    var clickTitle: (id:Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_contents, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.title.text = data[position].title
        holder.title.setOnClickListener{
            clickTitle(data[position].id)
        }
    }

    internal fun setList(lists: MutableList<KatanaData>){
        data = lists
    }
}