package com.wsr.katanarecorder.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wsr.katanarecorder.R
import com.wsr.katanarecorder.ui.detail.DetailActivity
import com.wsr.katanarecorder.ui.detail.DetailShowFragment
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private var recyclerView: RecyclerView? = null

    private lateinit var viewModel: ListViewModel
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

        listAdapter.clickTitle = {title ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("Title", title)
            startActivity(intent)
        }
    }
}