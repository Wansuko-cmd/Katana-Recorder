package com.wsr.katanarecorder.ui.detail.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wsr.katanarecorder.R
import com.wsr.katanarecorder.db.SampleModel
import com.wsr.katanarecorder.ui.detail.show.DetailShowEpoxyController
import com.wsr.katanarecorder.view_model.EditViewModel
import com.wsr.katanarecorder.view_model.ListViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_detail_edit.*
import kotlinx.android.synthetic.main.fragment_detail_show.*

class DetailEditFragment : Fragment() {
    private var recyclerView: RecyclerView? = null

    private val args: DetailEditFragmentArgs by navArgs()
    private var infoList: SampleModel = SampleModel(-1, "", "", "", "", "", "", "", "", "", "", "")
    private lateinit var viewModel: ListViewModel
    private lateinit var editViewModel: EditViewModel
    private lateinit var controller: DetailEditEpoxyController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_detail_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.id

        setToolbar()

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.list.observe(viewLifecycleOwner, { list ->
            list?.let {
                if (list.find { id == it.id } != null) {
                    infoList = list.find { id == it.id }!!
                    setListValue(infoList)
                    controller.setData(editViewModel)
                }
            }
        })

        editViewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)

        controller = DetailEditEpoxyController()
        controller.setData(editViewModel)

        val divider = DividerItemDecoration(
            requireContext(),
            LinearLayoutManager(requireContext()).orientation
        )
        recyclerView = edit_detail_recycler_view
        recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(divider)
            adapter = controller.adapter
        }
    }

    private fun setToolbar(){
        val toolbar = requireActivity().detail_toolbar
        toolbar.menu.setGroupVisible(R.id.show_menu_group, false)
        toolbar.menu.setGroupVisible(R.id.edit_menu_group, true)
        toolbar.setOnMenuItemClickListener{menuItem ->
            when(menuItem.itemId){
                R.id.save -> {

                }
            }
            true
        }
    }

    private fun setListValue(value: SampleModel){
        editViewModel.setValue("銘", value.title)
        editViewModel.setValue("種類", value.kind)
        editViewModel.setValue("産地", value.from)
        editViewModel.setValue("時代", value.age)
        editViewModel.setValue("刃長", value.length)
        editViewModel.setValue("反り", value.warp)
        editViewModel.setValue("刃文", value.hamon)
        editViewModel.setValue("地鉄", value.zigane)
        editViewModel.setValue("鋩子", value.edge)
        editViewModel.setValue("茎", value.grip)
        editViewModel.setValue("備考", value.remarks)
    }
}