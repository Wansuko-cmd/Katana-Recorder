package com.wsr.katanarecorder.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.wsr.katanarecorder.R
import com.wsr.katanarecorder.db.SampleModel
import com.wsr.katanarecorder.view_model.ListViewModel
import kotlinx.android.synthetic.main.fragment_detail_show.*

class DetailShowFragment: Fragment(){

    private val args: DetailShowFragmentArgs by navArgs()
    private lateinit var infoList: SampleModel
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
        viewModel =  ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.list.observe(viewLifecycleOwner, {list ->
            list?.let{
                infoList = if(list.find{id == it.id} != null) list.find{id == it.id}!!
                else SampleModel(-1, "Not Found","","","","","","","","","","")
            }
        })
    }
}