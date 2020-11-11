package com.wsr.katanarecorder.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wsr.katanarecorder.R
import com.wsr.katanarecorder.db.SampleModel
import com.wsr.katanarecorder.view_model.ListViewModel
import kotlinx.android.synthetic.main.fragment_detail_show.*

class DetailShowFragment: Fragment(){
    private var recyclerView: RecyclerView? = null

    private val args: DetailShowFragmentArgs by navArgs()
    private var infoList: SampleModel = SampleModel(-1,"re","","","","","","","","","","")
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

        val id: Int = args.id

        val controller = DetailEpoxyController()
        controller.setData(infoList)

        viewModel =  ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.list.observe(viewLifecycleOwner, {list ->
            list?.let{
                if(list.find{id == it.id} != null) {
                    infoList = list.find{id == it.id}!!
                    controller.setData(infoList)
                }

            }
        })

        recyclerView = show_detail_recycler_view
        recyclerView?.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = controller.adapter
        }
    }
}