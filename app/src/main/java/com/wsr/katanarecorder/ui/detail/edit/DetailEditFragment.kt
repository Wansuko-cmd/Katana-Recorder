package com.wsr.katanarecorder.ui.detail.edit

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wsr.katanarecorder.R
import com.wsr.katanarecorder.db.SampleModel
import com.wsr.katanarecorder.view_model.EditViewModel
import com.wsr.katanarecorder.view_model.ListViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_detail_edit.*

//編集画面用のフラグメント
class DetailEditFragment : Fragment() {
    private var recyclerView: RecyclerView? = null

    private val args: DetailEditFragmentArgs by navArgs()
    private lateinit var viewModel: ListViewModel
    private lateinit var editViewModel: EditViewModel
    private lateinit var controller: DetailEditEpoxyController
    private lateinit var observer: DetailEditImageSetter


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

        setToolbar()

        //編集するもののid
        val id = args.id


        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        viewModel.list.observe(viewLifecycleOwner, { newStatus ->
            if(id == -1) {
                editViewModel.setStatus(SampleModel(-1, "", null, mutableMapOf(
                    "銘" to "",
                    "種別" to "",
                    "地鉄" to "",
                    "刃文" to ""
                )))
            }else{
                newStatus.find{it.id == id}?.let{
                    editViewModel.setStatus(it)
                }
            }


            controller.setData(requireActivity(), editViewModel)
        })


        editViewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)

        //SAFをフラグメントで使うための準備
        observer = DetailEditImageSetter(requireActivity(), editViewModel, resetController)
        lifecycle.addObserver(observer)

        //editViewModelの中の変数の初期化
        editViewModel.setDetailEditImageSetter(observer)
        editViewModel.setActivity(requireActivity())

        //Epoxyのcontrollerを設定
        controller = DetailEditEpoxyController()
        controller.setData(requireActivity(), editViewModel)

        //枠ごとに線を引く処理
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

        Log.i("edit id:", id.toString())
    }

    //ツールバーの設定
    private fun setToolbar(){
        val toolbar = requireActivity().detail_toolbar
        toolbar.menu.setGroupVisible(R.id.show_menu_group, false)
        toolbar.menu.setGroupVisible(R.id.edit_menu_group, true)
        toolbar.setOnMenuItemClickListener{menuItem ->
            when(menuItem.itemId){
                R.id.save -> {
                    editViewModel.save()
                }
            }
            true
        }
    }

    //コントローラーのインスタンスを再生成して、再描画してもらうための処理
    private val resetController: (url: Uri) -> Unit = {
        editViewModel.setUrl(it)
        controller = DetailEditEpoxyController()
        controller.setData(activity, editViewModel)
        recyclerView!!.adapter = controller.adapter
    }
}