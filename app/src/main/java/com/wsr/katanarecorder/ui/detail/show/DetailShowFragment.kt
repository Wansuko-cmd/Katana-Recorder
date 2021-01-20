package com.wsr.katanarecorder.ui.detail.show

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wsr.katanarecorder.R
import com.wsr.katanarecorder.view_model.ListViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_detail_show.*

//詳細画面の動作部分
class DetailShowFragment: Fragment() {
    private var recyclerView: RecyclerView? = null
    private val args: DetailShowFragmentArgs by navArgs()
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_detail_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //表示している刀のid
        val id: Int = args.id

        //Epoxyのコントローラーのインスタンスの形成
        val controller = DetailShowEpoxyController()

        setToolbar()

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.list.observe(viewLifecycleOwner, { status ->
            status.find{ id == it.id}?.let {
                controller.setData(it)
            }
        })

        //枠ごとに線を引く処理
        val divider = DividerItemDecoration(
            requireContext(),
            LinearLayoutManager(requireContext()).orientation
        )

        recyclerView = show_detail_recycler_view
        recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(divider)
            adapter = controller.adapter
        }

        Log.i("show id:", id.toString())
    }

    //ツールバーの設定
    private fun setToolbar() {
        val toolbar = requireActivity().detail_toolbar
        toolbar.menu.setGroupVisible(R.id.show_menu_group, true)
        toolbar.menu.setGroupVisible(R.id.edit_menu_group, false)
        toolbar.setOnMenuItemClickListener{ menuItem ->
            when (menuItem.itemId) {
                R.id.edit_menu -> {
                    val action = DetailShowFragmentDirections.showDetailToEditDetail(args.id)
                    findNavController().navigate(action)
                }
            }
            true
        }
    }
}