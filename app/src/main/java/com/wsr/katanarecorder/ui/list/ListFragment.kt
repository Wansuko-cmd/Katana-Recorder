package com.wsr.katanarecorder.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wsr.katanarecorder.R
import com.wsr.katanarecorder.ui.detail.DetailActivity
import com.wsr.katanarecorder.view_model.ListViewModel
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

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        listAdapter = ListAdapter()

        this.recyclerView = list_recycler_view
        this.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        listAdapter.clickTitle = {id ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("ID", id)
            startActivity(intent)
        }

        viewModel.list.observe(viewLifecycleOwner, {list ->
            list?.let{listAdapter.setList(it)}
        })
    }
}