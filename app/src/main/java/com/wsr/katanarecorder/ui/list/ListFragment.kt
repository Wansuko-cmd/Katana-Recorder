package com.wsr.katanarecorder.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wsr.katanarecorder.R
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private var recyclerView: RecyclerView? = null

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var listAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = ListAdapter()

        this.recyclerView = list_recycler_view
        this.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }
}