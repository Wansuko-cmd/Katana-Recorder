package com.wsr.katanarecorder.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wsr.katanarecorder.R
import com.wsr.katanarecorder.db.SampleDB
import com.wsr.katanarecorder.db.SampleModel
import com.wsr.katanarecorder.repository.Repository

class ListAdapter :RecyclerView.Adapter<ListViewHolder>(){
    private var data: MutableList<SampleModel> = mutableListOf()

    var clickTitle: (title: String) -> Unit = {}

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
            clickTitle(holder.title.text.toString())
        }
    }

    internal fun setList(lists: MutableList<SampleModel>){
        data = lists
    }
}